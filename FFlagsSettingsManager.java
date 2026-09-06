package com.chevstrap.rbx;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.chevstrap.rbx.Utility.FileTool;
import com.chevstrap.rbx.Utility.FileToolAlt;
import com.chevstrap.rbx.Utility.INeedPath;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FFlagsSettingsManager {
    private final Context context;

    public FFlagsSettingsManager(Context context) {
        this.context = context;
    }

    public static String getPackageTarget(Context context) {
        String preferredApp = getSetting1(context, "PreferredRobloxApp");

        if (Objects.equals(preferredApp, "Roblox VN")) {
            return "com.roblox.client.vnggames";
        } else if (Objects.equals(preferredApp, "Roblox")) {
            return "com.roblox.client";
        }

        String[] robloxPackages = {
                "com.roblox.client.vnggames", // preferred VN variant
                "com.roblox.client"           // global fallback
        };

        for (String pkg : robloxPackages) {
            try {
                context.getPackageManager().getPackageInfo(pkg, 0);
                return pkg;
            } catch (PackageManager.NameNotFoundException ignored) {}
        }
        return null; // or default to one if needed

    }

    public static void applyFastFlag(Context context) throws IOException {
        // Se o usuário desativou explicitamente o gerenciador de flags, respeita e não aplica nada.
        // (Antes a checagem estava invertida e bloqueava justamente quem tinha ativado o recurso.)
        if (isExistSettingKey1(context, "UseFastFlagManager")) {
            boolean fastFlagManagerEnabled = Boolean.parseBoolean(getSetting1(context, "UseFastFlagManager"));
            if (!fastFlagManagerEnabled) {
                throw new IllegalStateException("Fast Flag Manager está desativado nas configurações");
            }
        }

        String targetPackage = getPackageTarget(context);
        if (targetPackage == null) {
            throw new IOException("Roblox não está instalado (nem o app clonado foi encontrado)");
        }

        String rbxpathh = INeedPath.getRBXPathDir(context, targetPackage);
        if (rbxpathh == null) {
            throw new IOException("Não foi possível localizar a pasta de dados do Roblox");
        }

        File clientSettingsDir = new File(context.getFilesDir(), "Modifications/ClientSettings");
        File outFile1 = new File(clientSettingsDir, "ClientAppSettings.json");

        if (!outFile1.exists()) {
            throw new IOException("Nenhum arquivo de flags encontrado para aplicar: " + outFile1.getAbsolutePath());
        }

        String content = FileTool.read(outFile1);
        if (content == null || content.trim().isEmpty()) {
            throw new IOException("Arquivo de flags vazio ou ilegível: " + outFile1.getAbsolutePath());
        }

        // Validação: nunca escrever JSON inválido no client do Roblox.
        // Um JSON quebrado pode fazer o Roblox ignorar todas as flags ou travar na inicialização.
        try {
            new JSONObject(content);
        } catch (Exception e) {
            throw new IOException("JSON de flags inválido, aplicação cancelada por segurança: " + e.getMessage());
        }

        String targetDirPath = rbxpathh + "exe/ClientSettings";
        String targetFilePath = targetDirPath + "/ClientAppSettings.json";

        if (FileToolAlt.isRootAvailable()) {
            FileToolAlt.createDirectoryWithPermissions(targetDirPath);

            if (!FileToolAlt.pathExists(targetDirPath)) {
                throw new IOException("Diretório bloqueado pelo SELinux ou inexistente: " + targetDirPath);
            }

            backupExistingFlags(targetFilePath, true);
            FileToolAlt.writeFile(targetFilePath, content);
            return;
        }

        // Fallback sem root (via app clonado com UID/sandbox compartilhado)
        File fallbackDir = new File(targetDirPath);
        File fallbackFile = new File(fallbackDir, "ClientAppSettings.json");

        if (!fallbackDir.exists() && !fallbackDir.mkdirs()) {
            throw new IOException("Falha ao criar a pasta ClientSettings: " + targetDirPath);
        }

        backupExistingFlags(fallbackFile.getAbsolutePath(), false);
        FileTool.write(fallbackFile, content);
    }

    /**
     * Guarda uma cópia do ClientAppSettings.json atual antes de sobrescrever,
     * para permitir reverter caso a nova configuração cause problemas no Roblox.
     */
    private static void backupExistingFlags(String targetFilePath, boolean viaRoot) {
        try {
            if (viaRoot) {
                if (FileToolAlt.pathExists(targetFilePath)) {
                    String previous = FileToolAlt.readFile(targetFilePath);
                    if (previous != null && !previous.trim().isEmpty()) {
                        FileToolAlt.writeFile(targetFilePath + ".bak", previous);
                    }
                }
            } else {
                File targetFile = new File(targetFilePath);
                if (targetFile.exists()) {
                    String previous = FileTool.read(targetFile);
                    if (previous != null && !previous.trim().isEmpty()) {
                        FileTool.write(new File(targetFilePath + ".bak"), previous);
                    }
                }
            }
        } catch (Exception ignored) {
            // Backup é best-effort; não deve impedir a aplicação das flags se falhar.
        }
    }

    public static boolean isExistSettingKey1(Context context, String keyName) {
        File filePath = new File(context.getFilesDir(), "AppSettings.json");

        if (!filePath.exists()) return false;

        try {
            JSONObject jsonObject = new JSONObject(FileTool.read(filePath));
            return jsonObject.has(keyName);
        } catch (Exception ignored) {
            return false;
        }
    }

    public static String getSetting1(Context context, String flagName) {
        File filePath = new File(context.getFilesDir(), "LastAppSettings.json");

        if (!filePath.exists()) return null;

        try {
            String content = FileTool.read(filePath);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.optString(flagName, null);
        } catch (Exception e) {
            return null;
        }
    }

    public String getPreset(String flagName) {
        File clientSettingsDir = new File(context.getFilesDir(), "Modifications/ClientSettings");
        File filePath = new File(clientSettingsDir, "LastClientAppSettings.json");

        if (!filePath.exists()) return null;

        try {
            String content = FileTool.read(filePath);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.optString(flagName, null);
        } catch (Exception e) {
            return null;
        }
    }

    public String getSetting(String flagName) {
        File filePath = new File(context.getFilesDir(), "AppSettings.json");

        if (!filePath.exists()) return null;

        try {
            String content = FileTool.read(filePath);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.optString(flagName, null);
        } catch (Exception e) {
            return null;
        }
    }
}
