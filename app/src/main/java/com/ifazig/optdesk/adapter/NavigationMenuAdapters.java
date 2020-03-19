package com.ifazig.optdesk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api_model.NavigationMenuModel;
import com.ifazig.optdesk.interfaces.NavigationClick;

import java.util.List;

/**
 * Created by Kannan on 12,Aug,2019
 */
public class NavigationMenuAdapters extends BaseAdapter {
    private final Context context;
    private final List<NavigationMenuModel> navigationMenuModels;
    private final NavigationClick navigationClick;

    public NavigationMenuAdapters(Context context, List<NavigationMenuModel> navigationMenuModels, NavigationClick navigationClick) {
        this.context = context;
        this.navigationMenuModels = navigationMenuModels;
        this.navigationClick = navigationClick;
    }

    @Override
    public int getCount() {
        return navigationMenuModels.size();
    }

    @Override
    public NavigationMenuModel getItem(int position) {
        return navigationMenuModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate( R.layout.adapter_navigation_menu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final NavigationMenuModel navigationItem = getItem(position);
        holder.ivNavigationIcon.setImageResource(navigationItem.getNavigationIcon());
        holder.tvNavigationName.setText(navigationItem.getNavigationItemName());



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationClick.onClick(navigationItem);
            }
        });
        return convertView;
    }

    static class ViewHolder {

        TextView tvNavigationName;
        ImageView ivNavigationIcon;
        ViewHolder(View view) {

            tvNavigationName = view.findViewById(R.id.tvNavigationName);
            ivNavigationIcon = view.findViewById(R.id.ivNavigationIcon);
        }
    }
}
