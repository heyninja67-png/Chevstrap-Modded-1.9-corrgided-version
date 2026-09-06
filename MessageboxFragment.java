package com.chevstrap.rbx.UI.Elements.CustomDialogs;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chevstrap.rbx.R;

public class MessageboxFragment extends DialogFragment {
	public interface MessageboxListener {
		void onOkClicked();
		void onCancelClicked();
	}

	private MessageboxListener listener;
	private String messageText = "Default message";

	private LinearLayout linear1, linear2, linear3, linear5, linear6;
	private TextView textview1, textview3;
	private Button button_ok, button_cancel;

	private boolean cancelDisabled = false; // <- new flag

	public void setMessageboxListener(MessageboxListener listener) {
		this.listener = listener;
	}

	public void setMessageText(String text) {
		this.messageText = text;
	}

	public void disableCancel() {
		this.cancelDisabled = true; // <- just set the flag
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.messagebox_fragment, container, false);
		initialize(view);
		initializeLogic();
		return view;
	}

	private void initialize(View view) {
		linear1 = view.findViewById(R.id.linear1);
		linear2 = view.findViewById(R.id.linear2);
		linear3 = view.findViewById(R.id.linear3);
		linear5 = view.findViewById(R.id.linear5);
		linear6 = view.findViewById(R.id.linear6);
		textview1 = view.findViewById(R.id.textview1);
		textview3 = view.findViewById(R.id.textview3);
		button_ok = view.findViewById(R.id.button_ok);
		button_cancel = view.findViewById(R.id.button_cancel);
	}

	private void initializeLogic() {
		AstyleButton(button_cancel);
		AstyleButton(button_ok);

		if (!messageText.isEmpty()) {
			textview3.setText(messageText);
		}

		if (cancelDisabled) {
			button_cancel.setVisibility(View.GONE);
		}

		button_ok.setOnClickListener(v -> {
			if (listener != null) listener.onOkClicked();
			dismiss();
		});

		button_cancel.setOnClickListener(v -> {
			if (listener != null) listener.onCancelClicked();
			dismiss();
		});
	}

	private void AstyleButton(Button button) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(15);
		drawable.setStroke(2, Color.parseColor("#0C0F19")); // Stroke color
		drawable.setColor(Color.parseColor("#000000"));     // Fill color
		button.setBackground(drawable);
	}
}
