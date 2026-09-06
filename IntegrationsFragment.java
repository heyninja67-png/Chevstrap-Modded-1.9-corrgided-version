package com.chevstrap.rbx.UI.Elements.Settings.Pages;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chevstrap.rbx.R;
import com.chevstrap.rbx.SettingsActivity;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class IntegrationsFragment extends Fragment {
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private TextView textview1;
	private TextView typecategory_textview4;
	private LinearLayout linear_hey1;
	private LinearLayout linear_hey2;
	private LinearLayout linear_info4;
	private LinearLayout linear_of_toggle4;
	private TextView textview_name_option4;
	private TextView textview_iswhat4;
	private ImageView imageview_switch1;
	private LinearLayout linear_info5;
	private LinearLayout linear_of_toggle5;
	private TextView textview_name_option5;
	private TextView textview_iswhat5;
	private ImageView imageview_switch2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.integrations_fragment, container, false);
		initialize(savedInstanceState, view);
		initializeLogic();
		return view;
	}

	private void initialize(Bundle savedInstanceState, View view) {
		vscroll1 = view.findViewById(R.id.vscroll1);
		linear2 = view.findViewById(R.id.linear2);
		textview1 = view.findViewById(R.id.textview1);
		typecategory_textview4 = view.findViewById(R.id.typecategory_textview4);
		linear_hey1 = view.findViewById(R.id.linear_hey1);
		linear_hey2 = view.findViewById(R.id.linear_hey2);
		linear_info4 = view.findViewById(R.id.linear_info4);
		linear_of_toggle4 = view.findViewById(R.id.linear_of_toggle4);
		textview_name_option4 = view.findViewById(R.id.textview_name_option4);
		textview_iswhat4 = view.findViewById(R.id.textview_iswhat4);
		imageview_switch1 = view.findViewById(R.id.imageview_switch1);
		linear_info5 = view.findViewById(R.id.linear_info5);
		linear_of_toggle5 = view.findViewById(R.id.linear_of_toggle5);
		textview_name_option5 = view.findViewById(R.id.textview_name_option5);
		textview_iswhat5 = view.findViewById(R.id.textview_iswhat5);
		imageview_switch2 = view.findViewById(R.id.imageview_switch2);

		imageview_switch1.setOnClickListener(v -> {
			if (getStateToggleByImage(imageview_switch1)) {
				try {
					linear_hey2.setAlpha(0.5f);
					saveSet("EnableActivityTracking", String.valueOf(false));
					saveSet("ShowServerDetails", String.valueOf(false));
					setStateToggleByImage(imageview_switch2, false);
				} catch (JSONException ignored) {

				}
			} else {
                try {
                    saveSet("EnableActivityTracking", String.valueOf(true));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                linear_hey2.setAlpha(1.0f);
            }
            toggleStateImage(imageview_switch1);
		});

		imageview_switch2.setOnClickListener(v -> {
			if (getStateToggleByImage(imageview_switch1)) {
				if (getStateToggleByImage(imageview_switch2)) {
					try {
						saveSet("ShowServerDetails", String.valueOf(false));
					} catch (JSONException ignored) {

					}
				} else {
					try {
						saveSet("ShowServerDetails", String.valueOf(true));
					} catch (JSONException ignored) {

					}
				}
				toggleStateImage(imageview_switch2);
			}
		});
	}

	private void initializeLogic() {
		AstyleButton1(linear_hey1);
		AstyleButton1(linear_hey2);

		setStateToggleByImage(imageview_switch1, getStateSettingKey("EnableActivityTracking"));
		setStateToggleByImage(imageview_switch2, getStateSettingKey("ShowServerDetails"));

		if (!getStateToggleByImage(imageview_switch1))  {
			linear_hey2.setAlpha(0.5f);
		}
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

	private void AstyleButton1(LinearLayout button) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(15);
		drawable.setStroke(2, Color.parseColor("#0C0F19")); // Stroke color
		drawable.setColor(Color.parseColor("#000000"));     // Fill color
		button.setBackground(drawable);
	}
}
