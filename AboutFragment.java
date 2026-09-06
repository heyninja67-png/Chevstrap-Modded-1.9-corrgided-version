package com.chevstrap.rbx.UI.Elements.CustomDialogs;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chevstrap.rbx.R;
import com.chevstrap.rbx.Utility.aboutApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class AboutFragment extends DialogFragment {

    private final ArrayList<HashMap<String, Object>> list_license = new ArrayList<>();
    public static Typeface arial;

    private ScrollView vscroll3;
    private LinearLayout linear9, linear10, linear11, linear12;
    private ListView listview1;
    private TextView textview1, textview2, textview3, textview6, textview_credits;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_fragment, container, false);
        initializeViews(rootView);
        initializeLogic();
        return rootView;
    }

    private void initializeViews(View view) {
        vscroll3 = view.findViewById(R.id.vscroll3);
        linear9 = view.findViewById(R.id.linear9);
        linear10 = view.findViewById(R.id.linear10);
        listview1 = view.findViewById(R.id.listview1);
        linear11 = view.findViewById(R.id.linear11);
        linear12 = view.findViewById(R.id.linear12);
        textview1 = view.findViewById(R.id.textview1);
        textview2 = view.findViewById(R.id.textview2);
        textview3 = view.findViewById(R.id.textview3);
        textview6 = view.findViewById(R.id.textview6);

        listview1.setOnItemClickListener((_param1, _param2, _param3, _param4) -> {
            String url = Objects.requireNonNull(list_license.get(_param3).get("url")).toString();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }

    private void initializeLogic() {
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Chevstrap");
            _item.put("url", "https://github.com/FrosSky/Chevstrap/blob/main/LICENSE");
            _item.put("license_type", "MIT License");
            list_license.add(_item);
        }
        listview1.setAdapter(new Listview1Adapter(list_license));
        ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();

        aboutApp app = new aboutApp(requireContext());
        textview1.setText("About Chevstrap " + app.getAppVersion());
    }

    public class Listview1Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }

        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            View _view = _v;
            if (_view == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                _view = inflater.inflate(R.layout.item_license, _container, false);
            }

            TextView textview1 = _view.findViewById(R.id.textview1);
            TextView textview2 = _view.findViewById(R.id.textview2);

            textview1.setText(getItem(_position).get("name") != null ? Objects.requireNonNull(getItem(_position).get("name")).toString() : "N/A");
            textview2.setText(getItem(_position).get("license_type") != null ? Objects.requireNonNull(getItem(_position).get("license_type")).toString() : "N/A");

            return _view;
        }
    }
}
