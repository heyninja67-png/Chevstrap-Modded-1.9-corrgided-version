package com.chevstrap.rbx.UI.Elements.CustomDialogs;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.widget.ImageView;

import com.chevstrap.rbx.R;

public class LoadingFragment extends DialogFragment {

	public interface MessageLoadingListener {
		void onOkClicked();
		void onCancelClicked();
	}

	private MessageLoadingListener listener;
    private LinearLayout button_cancel;
	private TextView textview_taskcurrently;
	private TextView textview_loadingstatus;
	private String pendingText = null;
	private String pendingStatus = null;

	public void setMessageboxListener(MessageLoadingListener listener) {
		this.listener = listener;
	}

	public void setMessageText(String text) {
		pendingText = text;
		if (textview_taskcurrently != null) {
			textview_taskcurrently.setText(text);
		}
	}

	public void setMessageStatus(String text) {
		pendingStatus = text;
		if (textview_loadingstatus != null) {
			textview_loadingstatus.setText(text);
		}
	}

	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.loading_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}

	private void initialize(Bundle _savedInstanceState, View _view) {
		LinearLayout linear_background = _view.findViewById(R.id.linear_background);
		LinearLayout linear2 = _view.findViewById(R.id.linear2);
		button_cancel = _view.findViewById(R.id.button_cancel);
		ImageView imageview_logo = _view.findViewById(R.id.imageview_logo);
		LinearLayout linear4 = _view.findViewById(R.id.linear4);
		textview_taskcurrently = _view.findViewById(R.id.textview_taskcurrently);
		textview_loadingstatus = _view.findViewById(R.id.textview_loadingstatus);

		if (pendingText != null) textview_taskcurrently.setText(pendingText);
		if (pendingStatus != null) textview_loadingstatus.setText(pendingStatus);
	}


	private void initializeLogic() {
		AstyleButton(button_cancel);

		button_cancel.setOnClickListener(v -> {
			if (listener != null) listener.onCancelClicked();
			dismiss(); // Close the dialog
		});
	}

	public String getMessageStatus() {
		if (textview_loadingstatus != null) {
			return textview_loadingstatus.getText().toString();
		}
		return "";
	}

	private void AstyleButton(LinearLayout button) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(15);
		drawable.setStroke(3, 0xFF101010);
		drawable.setColor(0xFF090909);
		button.setBackground(drawable);
	}
}