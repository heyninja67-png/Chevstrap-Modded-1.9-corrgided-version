package com.chevstrap.rbx.UI.Elements.Settings.Pages;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chevstrap.rbx.R;
import com.chevstrap.rbx.SettingsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FastflagsSettingsFragment extends Fragment {
	private final ArrayList<HashMap<String, Object>> list_map_fflag = new ArrayList<>();
	private List<String> itemListMSAA;
	private List<String> itemListRenderingAPI;
	private List<String> itemListTechnology;
	private List<String> itemListResolution;
	private List<String> itemListTextureQuality;
	private String auto = "";
	private String chosen = "";
	private String voxel = "";
	private String shadowMap = "";
	private String future = "";
	private String unified = "";

	private ScrollView vscroll1;
	private LinearLayout linear2;
	private TextView textview1;
	private LinearLayout linear_hey1, linear_hey2, linear_hey3, linear_hey4, linear_hey5,
			linear_hey6, linear_hey7, linear_hey8, linear_hey9, linear_hey010, linear_hey03, linear_hey011,
			linear_hey10, linear_hey11, linear_hey12, linear_hey13, linear_hey14,
			linear_hey15, linear_hey16, linear_hey17, linear_hey18, linear_hey19, linear_hey02;
	private TextView typecategory_textview4, typecategory_textview10, typecategory_textview15, typecategory_textview18;
	private ImageView image_of_button1;
	private LinearLayout linear_info1, linear4;
	private TextView textview_name_option1, textview_iswhat1;
	private ImageView imageview61;

	// Toggle groups and components
	private LinearLayout linear_info2, linear_of_toggle2;
	private TextView textview_name_option2, textview_iswhat2;
	private ImageView imageview_switch2;
	private ImageView imageview_switch02;
	private LinearLayout linear_info3;
	private Spinner spinner3, spinner4, spinner5, spinner03, spinner05;
	private TextView textview_name_option3, textview_iswhat3;

	private LinearLayout linear_info4, linear_of_toggle4;
	private TextView textview_name_option4, textview_iswhat4;
	private ImageView imageview_switch4;

	private LinearLayout linear_info5, linear_of_toggle5;
	private TextView textview_name_option5, textview_iswhat5;
	private ImageView imageview_switch5;

	private LinearLayout linear_info6, linear_of_toggle6;
	private TextView textview_name_option6, textview_iswhat6;
	private ImageView imageview_switch6;

	private LinearLayout linear_info7, linear_of_toggle7;
	private TextView textview_name_option7, textview_iswhat7;
	private ImageView imageview_switch7;

	private LinearLayout linear_info8, linear_edittext8;
	private TextView textview_name_option8, textview_iswhat8;
	private EditText edittext18;

	private LinearLayout linear_info9;
	private TextView textview_name_option9, textview_iswhat9;

	private LinearLayout linear_info010, linear_of_toggle010;
	private TextView textview_name_option010, textview_iswhat010;

	private LinearLayout linear_info10, linear_of_toggle10;
	private TextView textview_name_option10, textview_iswhat10;
	private ImageView imageview_switch10;

	private LinearLayout linear_info11, linear_of_toggle11;
	private TextView textview_name_option11, textview_iswhat11;
	private ImageView imageview_switch11;

	private LinearLayout linear_info12, linear_of_toggle12;
	private TextView textview_name_option12, textview_iswhat12;
	private ImageView imageview_switch12;

	private LinearLayout linear_info13, linear_edittext13;
	private TextView textview_name_option13, textview_iswhat13;
	private EditText edittext113;

	private LinearLayout linear_info14, linear_edittext14;
	private TextView textview_name_option14, textview_iswhat14;
	private EditText edittext114;

	private LinearLayout linear_info15, linear_of_toggle15;
	private TextView textview_name_option15, textview_iswhat15;
	private ImageView imageview_switch15;

	private LinearLayout linear_info16, linear_of_toggle16;
	private TextView textview_name_option16, textview_iswhat16;
	private ImageView imageview_switch16;

	private LinearLayout linear_info17, linear_edittext17;
	private TextView textview_name_option17, textview_iswhat17;
	private EditText edittext117;

	private LinearLayout linear_info18, linear_edittext18;
	private TextView textview_name_option18, textview_iswhat18;
	private EditText edittext118;

	private LinearLayout linear_info19, linear_of_toggle19;
	private TextView textview_name_option19, textview_iswhat19;
	private ImageView imageview_switch19;

    @Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.fastflags_settings_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		auto = getString(R.string.automatic);
		chosen = getString(R.string.chosen_text_by_game);
		voxel = getString(R.string.phase1_voxel);
		shadowMap = getString(R.string.phase2_shadow_map);
		future = getString(R.string.phase3_future);
		unified = getString(R.string.phase4_unified);

		itemListMSAA = Arrays.asList(auto, "1x", "2x", "4x", "8x");
		itemListRenderingAPI = Arrays.asList(auto, "Direct3D 10", "Direct3D 11", "Vulkan", "OpenGL");
		itemListTechnology = Arrays.asList(chosen, voxel, shadowMap, future, unified);
		itemListResolution = Arrays.asList(auto, "144p", "240p", "360p", "480p", "720p", "1080p", "1440p", "2160p", "4320p");
		itemListTextureQuality = Arrays.asList(auto, "1", "2", "3");

		linear_hey02 = _view.findViewById(R.id.linear_hey02);
		linear_hey03 = _view.findViewById(R.id.linear_hey03);
		linear_hey011 = _view.findViewById(R.id.linear_hey011);
		spinner05 = _view.findViewById(R.id.spinner05);
		vscroll1 = _view.findViewById(R.id.vscroll1);
		linear2 = _view.findViewById(R.id.linear2);
		textview1 = _view.findViewById(R.id.textview1);
		linear_hey1 = _view.findViewById(R.id.linear_hey1);
		linear_hey2 = _view.findViewById(R.id.linear_hey2);
		typecategory_textview4 = _view.findViewById(R.id.typecategory_textview4);
		linear_hey3 = _view.findViewById(R.id.linear_hey3);
		linear_hey4 = _view.findViewById(R.id.linear_hey4);
		linear_hey5 = _view.findViewById(R.id.linear_hey5);
		linear_hey6 = _view.findViewById(R.id.linear_hey6);
		linear_hey7 = _view.findViewById(R.id.linear_hey7);
		linear_hey8 = _view.findViewById(R.id.linear_hey8);
		linear_hey9 = _view.findViewById(R.id.linear_hey9);
		linear_hey010 = _view.findViewById(R.id.linear_hey010);
		typecategory_textview10 = _view.findViewById(R.id.typecategory_textview10);
		linear_hey10 = _view.findViewById(R.id.linear_hey10);
		linear_hey11 = _view.findViewById(R.id.linear_hey11);
		linear_hey12 = _view.findViewById(R.id.linear_hey12);
		linear_hey13 = _view.findViewById(R.id.linear_hey13);
		linear_hey14 = _view.findViewById(R.id.linear_hey14);
		typecategory_textview15 = _view.findViewById(R.id.typecategory_textview15);
		linear_hey15 = _view.findViewById(R.id.linear_hey15);
		linear_hey16 = _view.findViewById(R.id.linear_hey16);
		linear_hey17 = _view.findViewById(R.id.linear_hey17);
		typecategory_textview18 = _view.findViewById(R.id.typecategory_textview18);
		linear_hey18 = _view.findViewById(R.id.linear_hey18);
		linear_hey19 = _view.findViewById(R.id.linear_hey19);
		image_of_button1 = _view.findViewById(R.id.image_of_button1);
		linear_info1 = _view.findViewById(R.id.linear_info1);
		linear4 = _view.findViewById(R.id.linear4);
		textview_name_option1 = _view.findViewById(R.id.textview_name_option1);
		textview_iswhat1 = _view.findViewById(R.id.textview_iswhat1);
		imageview61 = _view.findViewById(R.id.imageview61);
		linear_info2 = _view.findViewById(R.id.linear_info2);
		linear_of_toggle2 = _view.findViewById(R.id.linear_of_toggle2);
		textview_name_option2 = _view.findViewById(R.id.textview_name_option2);
		textview_iswhat2 = _view.findViewById(R.id.textview_iswhat2);
		imageview_switch2 = _view.findViewById(R.id.imageview_switch2);
		imageview_switch02 = _view.findViewById(R.id.imageview_switch02);
		linear_info3 = _view.findViewById(R.id.linear_info3);
		spinner3 = _view.findViewById(R.id.spinner3);
		textview_name_option3 = _view.findViewById(R.id.textview_name_option3);
		textview_iswhat3 = _view.findViewById(R.id.textview_iswhat3);
		linear_info4 = _view.findViewById(R.id.linear_info4);
		linear_of_toggle4 = _view.findViewById(R.id.linear_of_toggle4);
		textview_name_option4 = _view.findViewById(R.id.textview_name_option4);
		textview_iswhat4 = _view.findViewById(R.id.textview_iswhat4);
		imageview_switch4 = _view.findViewById(R.id.imageview_switch4);
		linear_info5 = _view.findViewById(R.id.linear_info5);
		linear_of_toggle5 = _view.findViewById(R.id.linear_of_toggle5);
		textview_name_option5 = _view.findViewById(R.id.textview_name_option5);
		textview_iswhat5 = _view.findViewById(R.id.textview_iswhat5);
		imageview_switch5 = _view.findViewById(R.id.imageview_switch5);
		linear_info6 = _view.findViewById(R.id.linear_info6);
		linear_of_toggle6 = _view.findViewById(R.id.linear_of_toggle6);
		textview_name_option6 = _view.findViewById(R.id.textview_name_option6);
		textview_iswhat6 = _view.findViewById(R.id.textview_iswhat6);
		imageview_switch6 = _view.findViewById(R.id.imageview_switch6);
		linear_info7 = _view.findViewById(R.id.linear_info7);
		linear_of_toggle7 = _view.findViewById(R.id.linear_of_toggle7);
		textview_name_option7 = _view.findViewById(R.id.textview_name_option7);
		textview_iswhat7 = _view.findViewById(R.id.textview_iswhat7);
		imageview_switch7 = _view.findViewById(R.id.imageview_switch7);
		linear_info8 = _view.findViewById(R.id.linear_info8);
		linear_edittext8 = _view.findViewById(R.id.linear_edittext8);
		textview_name_option8 = _view.findViewById(R.id.textview_name_option8);
		textview_iswhat8 = _view.findViewById(R.id.textview_iswhat8);
		edittext18 = _view.findViewById(R.id.edittext18);
		linear_info9 = _view.findViewById(R.id.linear_info9);
		spinner4 = _view.findViewById(R.id.spinner4);
		textview_name_option9 = _view.findViewById(R.id.textview_name_option9);
		textview_iswhat9 = _view.findViewById(R.id.textview_iswhat9);
		linear_info010 = _view.findViewById(R.id.linear_info010);
		//linear_of_toggle010 = _view.findViewById(R.id.linear_of_toggle010);
		textview_name_option010 = _view.findViewById(R.id.textview_name_option010);
		textview_iswhat010 = _view.findViewById(R.id.textview_iswhat010);
		spinner5 = _view.findViewById(R.id.spinner5);
		linear_info10 = _view.findViewById(R.id.linear_info10);
		linear_of_toggle10 = _view.findViewById(R.id.linear_of_toggle10);
		textview_name_option10 = _view.findViewById(R.id.textview_name_option10);
		textview_iswhat10 = _view.findViewById(R.id.textview_iswhat10);
		imageview_switch10 = _view.findViewById(R.id.imageview_switch10);
		linear_info11 = _view.findViewById(R.id.linear_info11);
		linear_of_toggle11 = _view.findViewById(R.id.linear_of_toggle11);
		textview_name_option11 = _view.findViewById(R.id.textview_name_option11);
		textview_iswhat11 = _view.findViewById(R.id.textview_iswhat11);
		imageview_switch11 = _view.findViewById(R.id.imageview_switch11);
		linear_info12 = _view.findViewById(R.id.linear_info12);
		linear_of_toggle12 = _view.findViewById(R.id.linear_of_toggle12);
		textview_name_option12 = _view.findViewById(R.id.textview_name_option12);
		textview_iswhat12 = _view.findViewById(R.id.textview_iswhat12);
		imageview_switch12 = _view.findViewById(R.id.imageview_switch12);
		linear_info13 = _view.findViewById(R.id.linear_info13);
		linear_edittext13 = _view.findViewById(R.id.linear_edittext13);
		textview_name_option13 = _view.findViewById(R.id.textview_name_option13);
		textview_iswhat13 = _view.findViewById(R.id.textview_iswhat13);
		edittext113 = _view.findViewById(R.id.edittext113);
		linear_info14 = _view.findViewById(R.id.linear_info14);
		linear_edittext14 = _view.findViewById(R.id.linear_edittext14);
		textview_name_option14 = _view.findViewById(R.id.textview_name_option14);
		textview_iswhat14 = _view.findViewById(R.id.textview_iswhat14);
		edittext114 = _view.findViewById(R.id.edittext114);
		linear_info15 = _view.findViewById(R.id.linear_info15);
		linear_of_toggle15 = _view.findViewById(R.id.linear_of_toggle15);
		textview_name_option15 = _view.findViewById(R.id.textview_name_option15);
		textview_iswhat15 = _view.findViewById(R.id.textview_iswhat15);
		imageview_switch15 = _view.findViewById(R.id.imageview_switch15);
		linear_info16 = _view.findViewById(R.id.linear_info16);
		linear_of_toggle16 = _view.findViewById(R.id.linear_of_toggle16);
		textview_name_option16 = _view.findViewById(R.id.textview_name_option16);
		textview_iswhat16 = _view.findViewById(R.id.textview_iswhat16);
		spinner03 = _view.findViewById(R.id.spinner03);
		imageview_switch16 = _view.findViewById(R.id.imageview_switch16);
		linear_info17 = _view.findViewById(R.id.linear_info17);
		linear_edittext17 = _view.findViewById(R.id.linear_edittext17);
		textview_name_option17 = _view.findViewById(R.id.textview_name_option17);
		textview_iswhat17 = _view.findViewById(R.id.textview_iswhat17);
		edittext117 = _view.findViewById(R.id.edittext117);
		linear_info18 = _view.findViewById(R.id.linear_info18);
		linear_edittext18 = _view.findViewById(R.id.linear_edittext18);
		textview_name_option18 = _view.findViewById(R.id.textview_name_option18);
		textview_iswhat18 = _view.findViewById(R.id.textview_iswhat18);
		edittext118 = _view.findViewById(R.id.edittext118);
		linear_info19 = _view.findViewById(R.id.linear_info19);
		linear_of_toggle19 = _view.findViewById(R.id.linear_of_toggle19);
		textview_name_option19 = _view.findViewById(R.id.textview_name_option19);
		textview_iswhat19 = _view.findViewById(R.id.textview_iswhat19);
		imageview_switch19 = _view.findViewById(R.id.imageview_switch19);

		linear_hey1.setOnClickListener(_view1 -> {
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/FrosSky/Chevstrap/wiki/Fast-Flags-Guide-for-Android"));
			startActivity(browserIntent);
        });
		
		imageview_switch2.setOnClickListener(_view2 -> {
			if (getStateToggleByImage(imageview_switch2)) {
                try {
                    saveSet("UseFastFlagManager", "False");
                } catch (JSONException e) {
					throw new RuntimeException(e);
                }
            } else {
                try {
                    saveSet("UseFastFlagManager", "True");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
			toggleStateImage(imageview_switch2);
        });

		imageview_switch02.setOnClickListener(_view2 -> {
			if (getStateToggleByImage(imageview_switch02)) {
				if (requireActivity() instanceof SettingsActivity) {
					((SettingsActivity) requireActivity()).bringBackupToLast();
				}
			} else {
				if (requireActivity() instanceof SettingsActivity) {
					((SettingsActivity) requireActivity()).deleteWholeFastFlags();
				}
			}
			toggleStateImage(imageview_switch02);
		});

		spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				String selected = itemListMSAA.get(_param3);

				if (Objects.equals(selected, auto)) {
					removeFlag("FIntDebugForceMSAASamples");
				} else if (Objects.equals(selected, "1x")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FIntDebugForceMSAASamples");
					tempFlag.put("value", "1");
					temp_list_map_fflags.add(tempFlag);

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, "2x")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FIntDebugForceMSAASamples");
					tempFlag.put("value", "2");
					temp_list_map_fflags.add(tempFlag);

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, "4x")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FIntDebugForceMSAASamples");
					tempFlag.put("value", "4");
					temp_list_map_fflags.add(tempFlag);

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, "8x")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FIntDebugForceMSAASamples");
					tempFlag.put("value", "8");
					temp_list_map_fflags.add(tempFlag);

					sendFastFlagsToSettings(temp_list_map_fflags);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});

		ArrayAdapter<String> adapter5 = new ArrayAdapter<>(requireContext(), R.layout.custom_dropdown, R.id.textview1, itemListMSAA);
		spinner3.setAdapter(adapter5);

		spinner03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				String selected = itemListRenderingAPI.get(_param3);

				if (Objects.equals(selected, auto)) {
					removeFlag("FFlagDebugGraphicsPreferD3D11");
					removeFlag("FFlagDebugGraphicsPreferD3D11FL10");
					removeFlag("FFlagDebugGraphicsPreferVulkan");
					removeFlag("FFlagDebugGraphicsPreferOpenGL");
					removeFlag("FFlagDebugGraphicsDisableDirect3D11");
				} else if (Objects.equals(selected, "Direct3D 11")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FFlagDebugGraphicsPreferD3D11");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("FFlagDebugGraphicsDisableDirect3D11");
					removeFlag("FFlagDebugGraphicsPreferD3D11FL10");
					removeFlag("FFlagDebugGraphicsPreferVulkan");
					removeFlag("FFlagDebugGraphicsPreferOpenGL");

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, "Direct3D 10")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FFlagDebugGraphicsPreferD3D11FL10");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("FFlagDebugGraphicsDisableDirect3D11");
					removeFlag("FFlagDebugGraphicsPreferD3D11");
					removeFlag("FFlagDebugGraphicsPreferVulkan");
					removeFlag("FFlagDebugGraphicsPreferOpenGL");

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, "Vulkan")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag1 = new HashMap<>();
					tempFlag1.put("name", "FFlagDebugGraphicsDisableDirect3D11");
					tempFlag1.put("value", "True");
					temp_list_map_fflags.add(tempFlag1);

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FFlagDebugGraphicsPreferVulkan");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("FFlagDebugGraphicsPreferD3D11");
					removeFlag("FFlagDebugGraphicsPreferD3D11FL10");
					removeFlag("FFlagDebugGraphicsPreferOpenGL");

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, "OpenGL")) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag1 = new HashMap<>();
					tempFlag1.put("name", "FFlagDebugGraphicsDisableDirect3D11");
					tempFlag1.put("value", "True");
					temp_list_map_fflags.add(tempFlag1);

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FFlagDebugGraphicsPreferOpenGL");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("FFlagDebugGraphicsPreferD3D11");
					removeFlag("FFlagDebugGraphicsPreferD3D11FL10");
					removeFlag("FFlagDebugGraphicsPreferVulkan");

					sendFastFlagsToSettings(temp_list_map_fflags);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> _param1) {

			}
		});

		ArrayAdapter<String> adapter4 = new ArrayAdapter<>(requireContext(), R.layout.custom_dropdown, R.id.textview1, itemListRenderingAPI);
		spinner03.setAdapter(adapter4);

		spinner05.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				String selected = itemListTextureQuality.get(_param3);

				if (Objects.equals(selected, auto)) {
					removeFlag("DFIntTextureQualityOverride");
					removeFlag("DFFlagTextureQualityOverrideEnabled");
				} else if (selected.equals("1") || selected.equals("2") || selected.equals("3")) {
					ArrayList<HashMap<String, Object>> flags = new ArrayList<>();

					HashMap<String, Object> flag1 = new HashMap<>();
					flag1.put("name", "DFFlagTextureQualityOverrideEnabled");
					flag1.put("value", "True");
					flags.add(flag1);

					HashMap<String, Object> flag2 = new HashMap<>();
					flag2.put("name", "DFIntTextureQualityOverride");
					flag2.put("value", selected);
					flags.add(flag2);

					sendFastFlagsToSettings(flags);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> _param1) {

			}
		});

		ArrayAdapter<String> adapter04 = new ArrayAdapter<>(requireContext(), R.layout.custom_dropdown, R.id.textview1, itemListTextureQuality);
		spinner05.setAdapter(adapter04);

		imageview_switch4.setOnClickListener(_view3 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

			if (getStateToggleByImage(imageview_switch4)) {
				removeFlag("DFFlagDebugPauseVoxelizer");
				removeFlag("FIntRenderShadowIntensity");
				removeFlag("FIntRenderShadowmapBias");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "DFFlagDebugPauseVoxelizer");
				tempFlag1.put("value", "True");
				temp_list_map_fflags.add(tempFlag1);

				HashMap<String, Object> tempFlag2 = new HashMap<>();
				tempFlag2.put("name", "FIntRenderShadowIntensity");
				tempFlag2.put("value", "0");
				temp_list_map_fflags.add(tempFlag2);

				HashMap<String, Object> tempFlag3 = new HashMap<>();
				tempFlag3.put("name", "FIntRenderShadowmapBias");
				tempFlag3.put("value", "-1");
				temp_list_map_fflags.add(tempFlag3);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch4);
		});


		imageview_switch15.setOnClickListener(_view4 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

			if (getStateToggleByImage(imageview_switch15)) {
				removeFlag("DFIntBandwidthManagerApplicationDefaultBps");
				removeFlag("DFIntBandwidthManagerDataSenderMaxWorkCatchupMs");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "DFIntBandwidthManagerApplicationDefaultBps");
				tempFlag1.put("value", "96000");
				temp_list_map_fflags.add(tempFlag1);

				HashMap<String, Object> tempFlag2 = new HashMap<>();
				tempFlag2.put("name", "DFIntBandwidthManagerDataSenderMaxWorkCatchupMs");
				tempFlag2.put("value", "50");
				temp_list_map_fflags.add(tempFlag2);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch15);
		});

		imageview_switch16.setOnClickListener(_view4 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

			if (getStateToggleByImage(imageview_switch16)) {
				removeFlag("DFIntAssetPreloading");
				removeFlag("DFIntNumAssetsMaxToPreload");
				removeFlag("FStringGetPlayerImageDefaultTimeout");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "DFIntAssetPreloading");
				tempFlag1.put("value", "2147483647");
				temp_list_map_fflags.add(tempFlag1);

				HashMap<String, Object> tempFlag2 = new HashMap<>();
				tempFlag2.put("name", "DFIntNumAssetsMaxToPreload");
				tempFlag2.put("value", "2147483647");
				temp_list_map_fflags.add(tempFlag2);

				HashMap<String, Object> tempFlag3 = new HashMap<>();
				tempFlag3.put("name", "FStringGetPlayerImageDefaultTimeout");
				tempFlag3.put("value", "1");
				temp_list_map_fflags.add(tempFlag3);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch16);
		});

		edittext117.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {
				ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

				String input = _param1.toString();

				if (input.isEmpty()) {
					removeFlag("DFIntConnectionMTUSize");
				}
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "DFIntConnectionMTUSize");
				tempFlag1.put("value", input);
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
		});

		edittext118.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {
				ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();
				String input = _param1.toString();

				if (input.isEmpty()) {
					removeFlag("FStringDebugShowFlagState");
				}

				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FStringDebugShowFlagState");
				tempFlag1.put("value", input);
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
		});

		imageview_switch19.setOnClickListener(_view4 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

			if (getStateToggleByImage(imageview_switch19)) {
				removeFlag("DFFlagDebugPrintDataPingBreakDown");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "DFFlagDebugPrintDataPingBreakDown");
				tempFlag1.put("value", "True");
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch19);
		});

		imageview_switch5.setOnClickListener(_view4 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

			if (getStateToggleByImage(imageview_switch5)) {
				removeFlag("FFlagDisablePostFx");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FFlagDisablePostFx");
				tempFlag1.put("value", "True");
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch5);
        });
		
		imageview_switch6.setOnClickListener(_view5 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

			if (getStateToggleByImage(imageview_switch6)) {
				removeFlag("FIntFRMMaxGrassDistance");
				removeFlag("FIntRenderGrassDetailStrands");
				removeFlag("FIntFRMMinGrassDistance");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FIntFRMMaxGrassDistance");
				tempFlag1.put("value", "0");
				temp_list_map_fflags.add(tempFlag1);

				HashMap<String, Object> tempFlag2 = new HashMap<>();
				tempFlag2.put("name", "FIntRenderGrassDetailStrands");
				tempFlag2.put("value", "0");
				temp_list_map_fflags.add(tempFlag2);

				HashMap<String, Object> tempFlag3 = new HashMap<>();
				tempFlag3.put("name", "FIntFRMMinGrassDistance");
				tempFlag3.put("value", "0");
				temp_list_map_fflags.add(tempFlag3);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch6);
        });
		
		imageview_switch7.setOnClickListener(_view6 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();
			if (getStateToggleByImage(imageview_switch7)) {
				removeFlag("FFlagDebugSkyGray");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FFlagDebugSkyGray");
				tempFlag1.put("value", "True");
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}

			toggleStateImage(imageview_switch7);
        });

		imageview_switch11.setOnClickListener(_view6 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();
			if (getStateToggleByImage(imageview_switch11)) {
				removeFlag("FFlagDebugDisplayFPS");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FFlagDebugDisplayFPS");
				tempFlag1.put("value", "True");
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch11);
		});
		
		edittext18.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

				String input = _param1.toString();

				if (input.isEmpty()) {
					removeFlag("DFIntTaskSchedulerTargetFps");
				}

				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "DFIntTaskSchedulerTargetFps");
				tempFlag1.put("value", input);
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
		});

		edittext113.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();

			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {
				ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

				String input = _param1.toString();

				if (input.isEmpty()) {
					removeFlag("DFIntCanHideGuiGroupId");
					removeFlag("FFlagUserShowGuiHideToggles");
				}

				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "DFIntCanHideGuiGroupId");
				tempFlag1.put("value", input);
				temp_list_map_fflags.add(tempFlag1);

				HashMap<String, Object> tempFlag2 = new HashMap<>();
				tempFlag2.put("name", "FFlagUserShowGuiHideToggles");
				tempFlag2.put("value", input);
				temp_list_map_fflags.add(tempFlag2);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
		});

		edittext114.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

            }

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {
				ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

				String input = _param1.toString();

				if (input.isEmpty()) {
					removeFlag("FIntFontSizePadding");
				}

				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FIntFontSizePadding");
				tempFlag1.put("value", input);
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
		});
		
		spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				String selected = itemListTechnology.get(_param3);

				if (Objects.equals(selected, chosen)) {
					removeFlag("DFFlagDebugRenderForceTechnologyVoxel");
					removeFlag("FFlagDebugForceFutureIsBrightPhase2");
					removeFlag("FFlagDebugForceFutureIsBrightPhase3");
					removeFlag("FFlagRenderUnifiedLighting12");
				} else if (Objects.equals(selected, voxel)) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "DFFlagDebugRenderForceTechnologyVoxel");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("FFlagDebugForceFutureIsBrightPhase2");
					removeFlag("FFlagDebugForceFutureIsBrightPhase3");
					removeFlag("FFlagRenderUnifiedLighting12");

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, shadowMap)) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FFlagDebugForceFutureIsBrightPhase2");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("DFFlagDebugRenderForceTechnologyVoxel");
					removeFlag("FFlagDebugForceFutureIsBrightPhase3");
					removeFlag("FFlagRenderUnifiedLighting12");

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, future)) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FFlagDebugForceFutureIsBrightPhase3");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("DFFlagDebugRenderForceTechnologyVoxel");
					removeFlag("FFlagDebugForceFutureIsBrightPhase2");
					removeFlag("FFlagRenderUnifiedLighting12");

					sendFastFlagsToSettings(temp_list_map_fflags);
				} else if (Objects.equals(selected, unified)) {
					ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

					HashMap<String, Object> tempFlag = new HashMap<>();
					tempFlag.put("name", "FFlagRenderUnifiedLighting12");
					tempFlag.put("value", "True");
					temp_list_map_fflags.add(tempFlag);

					removeFlag("DFFlagDebugRenderForceTechnologyVoxel");
					removeFlag("FFlagDebugForceFutureIsBrightPhase2");
					removeFlag("FFlagDebugForceFutureIsBrightPhase3");

					sendFastFlagsToSettings(temp_list_map_fflags);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});

		ArrayAdapter<String> adapter2 = new ArrayAdapter<>(requireContext(), R.layout.custom_dropdown, R.id.textview1, itemListTechnology);
		spinner4.setAdapter(adapter2);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(), R.layout.custom_dropdown, R.id.textview1, itemListResolution);
		spinner5.setAdapter(adapter1);

		spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                String selected = itemListResolution.get(_param3);

				if (Objects.equals(selected, auto)) {
					removeFlag("DFIntDebugDynamicRenderKiloPixels");
					return;
				}

				// Extract vertical resolution number from string, e.g. "1080p" -> 1080
				int verticalPixels = 0;
				try {
					if (selected.endsWith("p")) {
						verticalPixels = Integer.parseInt(selected.substring(0, selected.length() - 1));
					}
				} catch (NumberFormatException e) {
					//e.printStackTrace();
					return;
				}

				if (verticalPixels == 0) {
					// Could not parse resolution, exit early
					return;
				}

				// Calculate horizontal pixels assuming 16:9 aspect ratio
				int horizontalPixels = (int) Math.round(verticalPixels * 16.0 / 9.0);

				// Calculate total pixels (resolution count)
				long totalPixels = (long) horizontalPixels * verticalPixels;

				// Convert to kilo pixels (thousands of pixels)
				long kiloPixels = totalPixels / 1000;

				// Prepare flags list
				ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();

				HashMap<String, Object> tempFlag = new HashMap<>();
				tempFlag.put("name", "DFIntDebugDynamicRenderKiloPixels");
				tempFlag.put("value", kiloPixels);  // Set calculated kilo pixels
				temp_list_map_fflags.add(tempFlag);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});

		imageview_switch10.setOnClickListener(_view7 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();
			if (getStateToggleByImage(imageview_switch10)) {
				removeFlag("FIntRobloxGuiBlurIntensity");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FIntRobloxGuiBlurIntensity");
				tempFlag1.put("value", "0");
				temp_list_map_fflags.add(tempFlag1);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch10);
        });

		imageview_switch12.setOnClickListener(_view7 -> {
			ArrayList<HashMap<String, Object>> temp_list_map_fflags = new ArrayList<>();
			if (getStateToggleByImage(imageview_switch12)) {
				removeFlag("FFlagEnablePreferredTextSizeScale");
				removeFlag("FFlagEnablePreferredTextSizeSettingInMenus2");
			} else {
				HashMap<String, Object> tempFlag1 = new HashMap<>();
				tempFlag1.put("name", "FFlagEnablePreferredTextSizeScale");
				tempFlag1.put("value", "True");
				temp_list_map_fflags.add(tempFlag1);

				HashMap<String, Object> tempFlag2 = new HashMap<>();
				tempFlag2.put("name", "FFlagEnablePreferredTextSizeSettingInMenus2");
				tempFlag2.put("value", "True");
				temp_list_map_fflags.add(tempFlag2);

				sendFastFlagsToSettings(temp_list_map_fflags);
			}
			toggleStateImage(imageview_switch12);
		});
	}
	
	private void initializeLogic() {
		boolean disablePostFx = getStateFlag("FFlagDisablePostFx");

		boolean debugPauseVoxelizer = getStateFlag("DFFlagDebugPauseVoxelizer");
		String renderShadowIntensity = getValueAsStringFlag("FIntRenderShadowIntensity");
		String renderShadowmapBias = getValueAsStringFlag("FIntRenderShadowmapBias");

		// Use cached value for imageview_switch5
		setStateToggleByImage(imageview_switch5, disablePostFx);

		// Only toggle imageview_switch4 if all three flags are true
		if (debugPauseVoxelizer) {
			if (Objects.equals(renderShadowIntensity, "0")) {
				if (Objects.equals(renderShadowmapBias, "-1")) {
					setStateToggleByImage(imageview_switch4, true);
				}
			}
		}

		// Cache the string values before checking
		String maxGrassDistance = getValueAsStringFlag("FIntFRMMaxGrassDistance");
		String minGrassDistance = getValueAsStringFlag("FIntFRMMinGrassDistance");
		String grassDetailStrands = getValueAsStringFlag("FIntRenderGrassDetailStrands");

		// Check all conditions safely
		if (Objects.equals(maxGrassDistance, "0")) {
			if (Objects.equals(minGrassDistance, "0")) {
				if (Objects.equals(grassDetailStrands, "0")) {
					setStateToggleByImage(imageview_switch6, true);
				}
			}
		}

		setStateToggleByImage(imageview_switch7, getStateFlag("FFlagDebugSkyGray"));
		setStateToggleByImage(imageview_switch11, getStateFlag("FFlagDebugDisplayFPS"));
		setStateToggleByImage(imageview_switch19, getStateFlag("DFFlagDebugPrintDataPingBreakDown"));

		if (IsExistFlag("FIntRobloxGuiBlurIntensity")) {
			if (Objects.equals(getValueAsStringFlag("FIntRobloxGuiBlurIntensity"), "0"))
				setStateToggleByImage(imageview_switch10, true);
		}

		if (Objects.equals(getValueAsStringFlag("DFIntBandwidthManagerApplicationDefaultBps"), "64000")) {
			if (Objects.equals(getValueAsStringFlag("DFIntBandwidthManagerDataSenderMaxWorkCatchupMs"), "20"))
				setStateToggleByImage(imageview_switch15, true);
		}

		if (Objects.equals(getValueAsStringFlag("DFIntAssetPreloading"), "2147483647")) {
			if (Objects.equals(getValueAsStringFlag("DFIntNumAssetsMaxToPreload"), "2147483647")) {
				if (Objects.equals(getValueAsStringFlag("FStringGetPlayerImageDefaultTimeout"), "1")) {
					setStateToggleByImage(imageview_switch16, true);
				}
			}
		}

		if (Objects.equals(getValueAsStringFlag("FFlagEnablePreferredTextSizeSettingInMenus2"), "True")) {
			if (Objects.equals(getValueAsStringFlag("FFlagEnablePreferredTextSizeScale"), "True"))
				setStateToggleByImage(imageview_switch12, true);
		}

		if (IsExistFlag("DFIntCanHideGuiGroupId")) {
			edittext113.setText(getValueAsStringFlag("DFIntCanHideGuiGroupId"));
		} else if (IsExistFlag("FFlagUserShowGuiHideToggles")) {
			edittext113.setText(getValueAsStringFlag("FFlagUserShowGuiHideToggles"));
		}

		if (IsExistFlag("FIntFontSizePadding")) {
			edittext117.setText(getValueAsStringFlag("FIntFontSizePadding"));
		}

		if (IsExistFlag("DFIntTaskSchedulerTargetFps")) {
			edittext18.setText(getValueAsStringFlag("DFIntTaskSchedulerTargetFps"));
		}

		if (IsExistFlag("DFIntConnectionMTUSize")) {
			edittext117.setText(getValueAsStringFlag("DFIntConnectionMTUSize"));
		}

		if (IsExistFlag("FIntFontSizePadding")) {
			edittext114.setText(getValueAsStringFlag("FIntFontSizePadding"));
		}

		if (IsExistFlag("FStringDebugShowFlagState")) {
			edittext118.setText(getValueAsStringFlag("FStringDebugShowFlagState"));
		}

		if (IsExistFlag("DFIntDebugDynamicRenderKiloPixels")) {
			String resolutionStr = getValueAsStringFlag("DFIntDebugDynamicRenderKiloPixels");
			int index = 0;

			try {
				int resolution = Integer.parseInt(resolutionStr); // This is kilo pixels
				String resolutionLabel = getClosestResolutionLabel((long) resolution * 1000L);
				index = itemListResolution.indexOf(resolutionLabel);
				if (index < 0) index = 0;
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner5.setSelection(index);
		}

		if (IsExistFlag("DFFlagDebugRenderForceTechnologyVoxel")) {
			int index = 0;

			try {
				index = itemListTechnology.indexOf(voxel);
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner4.setSelection(index);
		}

		if (IsExistFlag("FFlagDebugForceFutureIsBrightPhase2")) {
			int index = 0;

			try {
				index = itemListTechnology.indexOf(shadowMap);
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner4.setSelection(index);
		}

		if (IsExistFlag("FFlagDebugForceFutureIsBrightPhase3")) {
			int index = 0;

			try {
				index = itemListTechnology.indexOf(future);
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner4.setSelection(index);
		}

		if (IsExistFlag("FlagRenderUnifiedLighting12")) {
			int index = 0;

			try {
				index = itemListTechnology.indexOf(unified);
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner4.setSelection(index);
		}

		if (IsExistFlag("FIntDebugForceMSAASamples")) {
			String msaaStr = getValueAsStringFlag("FIntDebugForceMSAASamples");
			int selectedIndex = 0;

			try {
				int msaaValue = Integer.parseInt(msaaStr);
				String formattedMsaa = msaaValue + "x";
				selectedIndex = itemListMSAA.indexOf(formattedMsaa);

				if (selectedIndex < 0) {
					selectedIndex = 0;
				}
			} catch (NumberFormatException e) {
				// Optional: log the error if needed
				//selectedIndex = 0;
			}

			spinner3.setSelection(selectedIndex);
		}

		if (IsExistFlag("FFlagDebugGraphicsPreferD3D11")) {
			int index = 0;

			try {
				index = itemListRenderingAPI.indexOf("Direct3D 11");
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner03.setSelection(index);
		}

		if (IsExistFlag("FFlagDebugGraphicsPreferD3D11FL10")) {
			int index = 0;

			try {
				index = itemListRenderingAPI.indexOf("Direct3D 10");
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner03.setSelection(index);
		}

		if (IsExistFlag("FFlagDebugGraphicsPreferVulkan")) {
			int index = 0;

			try {
				index = itemListRenderingAPI.indexOf("Vulkan");
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner03.setSelection(index);
		}

		if (IsExistFlag("FFlagDebugGraphicsPreferOpenGL")) {
			int index = 0;

			try {
				index = itemListRenderingAPI.indexOf("OpenGL");
				if (index < 0) {
					index = 0;
				}
			} catch (NumberFormatException ignored) {
				// Optional: handle parse errors here
			}
			spinner03.setSelection(index);
		}

		if (IsExistFlag("DFIntTextureQualityOverride") && IsExistFlag("DFFlagTextureQualityOverrideEnabled")) {
			String msaaStr = getValueAsStringFlag("DFIntTextureQualityOverride");
			int selectedIndex = 0;

			try {
				int msaaValue = Integer.parseInt(msaaStr);
				String formattedMsaa = String.valueOf(msaaValue);
				selectedIndex = itemListTextureQuality.indexOf(formattedMsaa);

				if (selectedIndex < 0) {
					selectedIndex = 0;
				}
			} catch (NumberFormatException e) {
				// Optional: log the error if needed
				//selectedIndex = 0;
			}

			spinner05.setSelection(selectedIndex);
		}

		applySpinnerStyles();
		applyLinearLayoutStyles();
	}
	private String getClosestResolutionLabel(long totalPixels) {
		double height = Math.sqrt(totalPixels * 9.0 / 16.0);
		int roundedHeight = (int) Math.round(height);
		System.out.println("Total Pixels: " + totalPixels + ", Computed height: " + roundedHeight);

		String bestMatch = getString(R.string.automatic); // default
		int smallestDifference = Integer.MAX_VALUE;

		for (String label : itemListResolution) {
			if (label.equals(getString(R.string.automatic))) continue;

			int labelHeight;
			try {
				labelHeight = Integer.parseInt(label.replace("p", ""));
			} catch (NumberFormatException e) {
				continue;
			}

			int difference = Math.abs(labelHeight - roundedHeight);
			System.out.println("Checking label: " + label + ", difference: " + difference);

			if (difference < smallestDifference) {
				smallestDifference = difference;
				bestMatch = label;
			}
		}

		System.out.println("Best match: " + bestMatch);
		return bestMatch;
	}

	public boolean getStateToggleByImage(ImageView imageItem) {
		Drawable background = imageItem.getBackground();
		Drawable switchOff = ContextCompat.getDrawable(imageItem.getContext(), R.drawable.toggle_off);
		Drawable switchOn = ContextCompat.getDrawable(imageItem.getContext(), R.drawable.toggle_on);

		if (background != null && background.getConstantState() != null) {
			if (switchOff != null && background.getConstantState().equals(switchOff.getConstantState())) {
				return false;
			} else return switchOn != null && background.getConstantState().equals(switchOn.getConstantState());
		}
		return false; // Default state
	}

	private void applyLinearLayoutStyles() {
		LinearLayout[] linearLayouts = new LinearLayout[]{
				linear_hey03, linear_hey1, linear_hey2, linear_hey3, linear_hey4, linear_hey5,
				linear_hey6, linear_hey7, linear_hey8, linear_hey9, linear_hey010,
				linear_hey10, linear_hey11, linear_hey12, linear_hey13, linear_hey14,
				linear_hey15, linear_hey16, linear_hey17, linear_hey18, linear_hey19,
				linear_edittext8, linear_edittext13, linear_edittext14,
				linear_edittext17, linear_edittext18, linear_hey02, linear_hey011
		};

		for (LinearLayout layout : linearLayouts) {
			if (layout != null) AstyleButton1(layout);
		}
	}


	private void applySpinnerStyles() {
		Spinner[] spinners = new Spinner[]{
				spinner3, spinner4, spinner5, spinner03, spinner05
		};

		for (Spinner spinner : spinners) {
			if (spinner != null) AstyleButton2(spinner);
		}
	}

	private void AstyleButton1(LinearLayout button) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(15);
		drawable.setStroke(2, Color.parseColor("#0C0F19")); // Stroke color
		drawable.setColor(Color.parseColor("#000000"));     // Fill color
		button.setBackground(drawable);
	}

	private void AstyleButton2(Spinner button) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(15);
		drawable.setStroke(2, Color.parseColor("#0C0F19")); // Stroke color
		drawable.setColor(Color.parseColor("#000000"));     // Fill color
		button.setBackground(drawable);
	}

	public void setStateToggleByImage(ImageView imageItem, boolean state) {
		if (state) {
			imageItem.setBackgroundResource(R.drawable.toggle_on);
		} else {
			imageItem.setBackgroundResource(R.drawable.toggle_off);
		}
	}

	public void toggleStateImage(ImageView imageItem) {
		boolean currentState = getStateToggleByImage(imageItem);
		setStateToggleByImage(imageItem, !currentState);
	}

	public void removeFlag(String flagName) {
		if (requireActivity() instanceof SettingsActivity) {
			((SettingsActivity) requireActivity()).removeFlagKey(flagName);
		} else {
			Log.e("FastFlags", "Error removing flag: " + flagName);
		}
	}

	public boolean IsExistFlag(String flagName) {
		if (requireActivity() instanceof SettingsActivity) {
			String preset = ((SettingsActivity) requireActivity()).getPreset(flagName);
			return preset != null && !preset.isEmpty(); // true if exists and not empty
		}
		return false;
    }

	public boolean getStateFlag(String flagName) {
        return Boolean.parseBoolean(((SettingsActivity) requireActivity()).getPreset(flagName));
	}

	public String getValueAsStringFlag(String flagName) {
		if (requireActivity() instanceof SettingsActivity) {
			return ((SettingsActivity) requireActivity()).getPreset(flagName);
		}
		return "";
	}

	public void saveSet(String key, String value) throws JSONException {
		if (requireActivity() instanceof SettingsActivity) {
			((SettingsActivity) requireActivity()).saveSet(key, value);
		}
	}

	public boolean getStateSettingKey(String key) {
		if (requireActivity() instanceof SettingsActivity) {
			return Boolean.parseBoolean(((SettingsActivity) requireActivity()).getSetting(key));
		}
		return false;
	}

	public void removeSet(String flagName) {
		if (requireActivity() instanceof SettingsActivity) {
			((SettingsActivity) requireActivity()).removeSettingKey(flagName);
		} else {
			Log.e("Settings", "Error removing setting flag: " + flagName);
		}
	}

	public void sendFastFlagsToSettings(ArrayList<HashMap<String, Object>> HereList) {
		try {
			JSONObject jsonObject = new JSONObject();
			for (HashMap<String, Object> map : HereList) {
				jsonObject.put((String) map.get("name"), map.get("value"));
			}
			String jsonString = jsonObject.toString(4);
			if (requireActivity() instanceof SettingsActivity) {
				((SettingsActivity) requireActivity()).receiveFastFlagsList(jsonString);
			} else {
				Log.e("FastFlags", "Parent activity is not SettingsActivity");
			}
		} catch (Exception e) {
			Toast.makeText(requireContext(), "E: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			Log.e("JSON", "Error sending to settings: " + e.getMessage());
		}
	}
}