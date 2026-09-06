package com.chevstrap.rbx.UI.Elements.Settings.Pages;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chevstrap.rbx.R;
import com.chevstrap.rbx.SettingsActivity;
import com.chevstrap.rbx.UI.Elements.CustomDialogs.AddFastFlagDialogFragment;
import com.chevstrap.rbx.Models.ListFFlagsPresets;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FastflagsEditorFragment extends Fragment {
	private final ArrayList<HashMap<String, Object>> list_map_fflag = new ArrayList<>();
	private int selectedFlagIndex = -1;
	private ListView listview_flag;
	private TextView textview4;
	private static Boolean showPreset = false;
	private static String targetFlagClicked;
	private static Boolean isAllowedDeleteSelected;

    @NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fastflags_editor_fragment, container, false);
		initialize(view);
		initializeLogic();
		return view;
	}

	private void initialize(View view) {
		listview_flag = view.findViewById(R.id.listview_flag);

		//LinearLayout button_back = view.findViewById(R.id.button_back);
		LinearLayout button_addnew = view.findViewById(R.id.button_addnew);
		LinearLayout button_deleteall = view.findViewById(R.id.button_deleteall);
		LinearLayout button_deleteselected = view.findViewById(R.id.button_deleteselected);
		LinearLayout button_sourceflag = view.findViewById(R.id.button_showpresetflags);
		LinearLayout button_copyjson = view.findViewById(R.id.button_copyjson);
		LinearLayout linear1 = view.findViewById(R.id.linear1);
		EditText edittext1 = view.findViewById(R.id.edittext1);

		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();

			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				String query = editable.toString().toLowerCase();

				ArrayList<HashMap<String, Object>> originalList;
				ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();

				originalList = list_map_fflag;

				// Filter logic
				for (HashMap<String, Object> item : originalList) {
					String name = item.containsKey("name") ? Objects.requireNonNull(item.get("name")).toString().replace("_÷_", "\n") : "NoN";
					if (name.toLowerCase().contains(query)) {
						filteredList.add(item);
					}
				}

				listview_flag.setAdapter(new ListviewFlagAdapter(filteredList));
				((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();
			}
		});

		listview_flag.setOnItemClickListener((parent, view12, position, id) -> {
			selectedFlagIndex = position;
			if (Objects.equals(targetFlagClicked, list_map_fflag.get(position).get("name"))) {
				isAllowedDeleteSelected = false;
				targetFlagClicked = "";
				((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();
			} else {
				isAllowedDeleteSelected = true;
				targetFlagClicked = (String) list_map_fflag.get(position).get("name");
				((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();
			}
		});

		button_addnew.setOnClickListener(v -> {
			AddFastFlagDialogFragment dialog = new AddFastFlagDialogFragment();
			dialog.show(requireActivity().getSupportFragmentManager(), "AddFastFlag");
		});

		button_deleteselected.setOnClickListener(_view3 -> {
			// Check if selectedFlagIndex is valid
			if (isAllowedDeleteSelected) {
				if (selectedFlagIndex < 0 || selectedFlagIndex >= list_map_fflag.size()) {
					return;
				}

				HashMap<String, Object> removedItem = list_map_fflag.remove(selectedFlagIndex);
				selectedFlagIndex = -1;

				// Notify adapter that data has changed
				((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();

				if (requireActivity() instanceof SettingsActivity && removedItem != null) {
					((SettingsActivity) requireActivity()).removeFlagKey((String) removedItem.get("name"));
				}

				Log.d("RemoveDebug", "Removed item: " + (removedItem != null ? removedItem.get("name") : "null"));
			}
		});

		button_sourceflag.setOnClickListener(v -> {
			showPreset = !showPreset;
			((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();
		});

		button_deleteall.setOnClickListener(v -> {
			deleteWholeFastFlags();
		});

		button_copyjson.setOnClickListener(v -> {
			try {
				JSONObject jsonObject = new JSONObject();
				for (HashMap<String, Object> map : list_map_fflag) {
					String name = (String) map.get("name");
					Object value = map.get("value");
					jsonObject.put(name, value);
				}

				String jsonString = jsonObject.toString(4);
				ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
				ClipData clip = ClipData.newPlainText("FFlags JSON", jsonString);
				clipboard.setPrimaryClip(clip);
			} catch (Exception e) {
				Log.e("JSON", "Error copy JSON: " + e.getMessage());
			}
		});

		AstyleButton(button_deleteall);
		AstyleButton(button_addnew);
		AstyleButton(button_deleteselected);
		AstyleButton(button_sourceflag);
		AstyleButton(button_copyjson);
		AstyleButton(linear1);
	}

	private void initializeLogic() {
		try {
			String jsonString = ((SettingsActivity) requireActivity()).getCurrentFFlag();
			JSONObject jsonObject = new JSONObject(jsonString);

			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = jsonObject.get(key);

				HashMap<String, Object> map = new HashMap<>();
				map.put("name", key);
				map.put("value", value);

				list_map_fflag.add(map);
			}
		} catch (Exception e) {
			Log.e("JSON", "Error loading JSON: " + e.getMessage());
		}
		sendFastFlagsToSettings(list_map_fflag);
		listview_flag.setAdapter(new ListviewFlagAdapter(list_map_fflag));
		((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();
	}

	public void receiveFastFlagsListByDialog(String data) {
		try {
			JSONObject jsonObject = new JSONObject(data);
			Iterator<String> keys = jsonObject.keys();

			while (keys.hasNext()) {
				String key = keys.next();
				Object value = jsonObject.get(key);

				HashMap<String, Object> map = new HashMap<>();
				map.put("name", key);
				map.put("value", value);

				list_map_fflag.add(map);
			}

			sendFastFlagsToSettings(list_map_fflag);
			((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();
		} catch (Exception e) {
			Log.e("JSON", "Error receiving JSON: " + e.getMessage());
		}
	}

	public void sendFastFlagsToSettings(List<HashMap<String, Object>> flagsList) {
		try {
			JSONObject jsonObject = new JSONObject();
			for (HashMap<String, Object> map : flagsList) {
				jsonObject.put((String) map.get("name"), map.get("value"));
			}

			String jsonString = jsonObject.toString(4);
			Log.d("FastFlags", "Sending JSON: " + jsonString);

			if (requireActivity() instanceof SettingsActivity) {
				((SettingsActivity) requireActivity()).receiveFastFlagsList(jsonString);
			} else {
				Log.e("FastFlags", "Parent activity is not SettingsActivity");
			}
		} catch (Exception e) {
			Log.e("FastFlags", "Error sending: " + e.getMessage());
		}
	}

	public void deleteWholeFastFlags() {
		if (requireActivity() instanceof SettingsActivity) {
			((SettingsActivity) requireActivity()).showMessageBoxDeleteAll();
		} else {
			Log.e("FastFlags", "Parent activity is not SettingsActivity");
		}
	}

	private void AstyleButton(LinearLayout button) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(15);
		drawable.setStroke(2, Color.parseColor("#0C0F19")); // Stroke color
		drawable.setColor(Color.parseColor("#000000"));     // Fill color
		button.setBackground(drawable);
	}

	public void requestCleanListFlag() {
		list_map_fflag.clear();
		((BaseAdapter) listview_flag.getAdapter()).notifyDataSetChanged();
	}

	public void get(String path, String type) {
        File file = new File(path);
		Uri uri;
        uri = Uri.parse(file.getPath());
        Intent intent;
		try {
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType(type);
			intent.putExtra(Intent.EXTRA_STREAM, uri);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}catch (Exception e){
			throw new  RuntimeException(e);
		}
		startActivity(Intent.createChooser(intent,"Share Fast Flags"));
	}

//	private void parseJson(String json, ArrayList<HashMap<String, Object>> originalList) {
//		try {
//			JSONArray jsonArray = new JSONArray(json);
//			for (int i = 0; i < jsonArray.length(); i++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				HashMap<String, Object> map = new HashMap<>();
//
//				Iterator<String> keys = jsonObject.keys();
//				while (keys.hasNext()) {
//					String key = keys.next();
//					Object value = jsonObject.get(key);
//					map.put(key, value);
//				}
//				Log.d("JSON", "Parsed item: " + map);
//				originalList.add(map);
//			}
//		} catch (JSONException e) {
//			//e.printStackTrace();
//		}
//	}

	private static Boolean isShowPreset(String flag) {
		ListFFlagsPresets presets = new ListFFlagsPresets();
		HashMap<String, Object> fastFlags = presets.getListPresetString();
		if (fastFlags == null) {
			return false;
		}
		return fastFlags.containsKey(flag);
	}

	public static class ListviewFlagAdapter extends BaseAdapter {
		private final ArrayList<HashMap<String, Object>> data;

		public ListviewFlagAdapter(ArrayList<HashMap<String, Object>> data) {
			this.data = data;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fastflag_preset_editor, parent, false);
			}

			if (showPreset) {
				if (isShowPreset((String) getItem(position).get("name"))) {
					LinearLayout linear1 = convertView.findViewById(R.id.linear1);

					GradientDrawable drawable1 = new GradientDrawable();
					drawable1.setColor(Color.parseColor("#007221"));
					linear1.setBackground(drawable1);
				} else {
					LinearLayout linear1 = convertView.findViewById(R.id.linear1);

					GradientDrawable drawable1 = new GradientDrawable();
					drawable1.setColor(Color.parseColor("#72002d"));
					linear1.setBackground(drawable1);
				}
			} else {
				LinearLayout linear1 = convertView.findViewById(R.id.linear1);
				Map<String, Object> item = data.get(position);
				if (targetFlagClicked == item.get("name")) {
					GradientDrawable drawable2 = new GradientDrawable();
					drawable2.setColor(Color.parseColor("#3B4550"));
					linear1.setBackground(drawable2);
				} else {
					GradientDrawable drawable2 = new GradientDrawable();
					drawable2.setColor(Color.TRANSPARENT);
					linear1.setBackground(drawable2);
				}
			}

            Map<String, Object> item = data.get(position);

			TextView textview_name = convertView.findViewById(R.id.textview_name);
			TextView textview_value = convertView.findViewById(R.id.textview_value);

			textview_name.setText(item.get("name") != null ? Objects.requireNonNull(item.get("name")).toString() : "N/A");
			textview_value.setText(item.get("value") != null ? Objects.requireNonNull(item.get("value")).toString() : "N/A");

			return convertView;
		}
	}
}
