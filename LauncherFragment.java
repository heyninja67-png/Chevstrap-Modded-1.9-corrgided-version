package com.chevstrap.rbx.UI.Elements.Settings.Pages;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.chevstrap.rbx.R;
import com.chevstrap.rbx.SettingsActivity;

import org.json.JSONException;

public class LauncherFragment extends Fragment {
	private List<String> itemListPreferredRBXApp;
    private Bundle savedInstanceState;
    private ScrollView vscroll1;
	private LinearLayout linear2;
	private TextView textview1;
	private LinearLayout linear_hey3;
	private LinearLayout linear_hey5;
	private LinearLayout linear_info3;
	private Spinner spinner3;
	private TextView textview_name_option3;
	private TextView textview_iswhat3;
	private LinearLayout linear_info5;
	private LinearLayout linear_of_toggle5;
	private TextView textview_name_option5;
	private TextView textview_iswhat5;
	private ImageView imageview_switch5;
	private String auto;
	
	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.launcher_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		auto = getString(R.string.automatic);

		itemListPreferredRBXApp = Arrays.asList(auto, "Roblox", "Roblox VN");

		vscroll1 = _view.findViewById(R.id.vscroll1);
		linear2 = _view.findViewById(R.id.linear2);
		textview1 = _view.findViewById(R.id.textview1);
		linear_hey3 = _view.findViewById(R.id.linear_hey3);
		linear_hey5 = _view.findViewById(R.id.linear_hey5);
		linear_info3 = _view.findViewById(R.id.linear_info3);
		spinner3 = _view.findViewById(R.id.spinner3);
		textview_name_option3 = _view.findViewById(R.id.textview_name_option3);
		textview_iswhat3 = _view.findViewById(R.id.textview_iswhat3);
		linear_info5 = _view.findViewById(R.id.linear_info5);
		linear_of_toggle5 = _view.findViewById(R.id.linear_of_toggle5);
		textview_name_option5 = _view.findViewById(R.id.textview_name_option5);
		textview_iswhat5 = _view.findViewById(R.id.textview_iswhat5);
		imageview_switch5 = _view.findViewById(R.id.imageview_switch5);

		spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				String selected = itemListPreferredRBXApp.get(_param3);
				try {
					if (selected.equals(auto)) {
						removeSet("PreferredRobloxApp");
					} else {
						saveSet("PreferredRobloxApp", selected);
					}
				} catch (JSONException e) {
					Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> _param1) {}
		});

		imageview_switch5.setOnClickListener(v -> {
			if (getStateToggleByImage(imageview_switch5)) {
				try {
					saveSet("UseLegacyFinderPath", String.valueOf(false));
				} catch (JSONException ignored) {

				}
			} else {
				try {
					saveSet("UseLegacyFinderPath", String.valueOf(true));
				} catch (JSONException ignored) {

				}
			}
			toggleStateImage(imageview_switch5);
		});
	}

	private void initializeLogic() {
		ArrayAdapter<String> adapter5 = new ArrayAdapter<>(requireContext(), R.layout.custom_dropdown, R.id.textview1, itemListPreferredRBXApp);
		spinner3.setAdapter(adapter5);

		AstyleButton1(linear_hey5);
		AstyleButton1(linear_hey3);
		AstyleButton2(spinner3);

		// Handle setting selection
		int selectedIndex; // default to "Automatic"
		if (IsExistSettingKey("PreferredRobloxApp")) {
			String savedApp = getValueAsStringSettingKey("PreferredRobloxApp");
			int index = itemListPreferredRBXApp.indexOf(savedApp);
			if (index >= 0) {
				selectedIndex = index;
			} else {
				// Saved app not in list – fallback to Automatic
				selectedIndex = itemListPreferredRBXApp.indexOf(auto);
				if (selectedIndex < 0) selectedIndex = 0;
			}
		} else {
			// No saved setting – use "Automatic"
			selectedIndex = itemListPreferredRBXApp.indexOf(auto);
			if (selectedIndex < 0) selectedIndex = 0;
		}

		final int finalSelectedIndex = selectedIndex;
		spinner3.post(() -> spinner3.setSelection(finalSelectedIndex));

		// Set toggle state
		setStateToggleByImage(imageview_switch5, getStateSettingKey("UseLegacyFinderPath"));
	}

	public boolean IsExistSettingKey(String FlagName) {
		if (requireActivity() instanceof SettingsActivity) {
			return ((SettingsActivity) requireActivity()).isExistSettingKey(FlagName);
		}
		return false;
	}

	public String getValueAsStringSettingKey(String key) {
		if (requireActivity() instanceof SettingsActivity) {
			return ((SettingsActivity) requireActivity()).getValueAsStringSettingKey(key);
		}
		return "";
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
}
