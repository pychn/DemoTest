package demo.py.com.demo;

import android.util.SparseArray;
import android.view.View;

/**
 * ListView使用ViewHolder极简写法
 * User: py
 * Date: 2015-02-04
 * Time: 16:18
 */
public class GetViewHodler {
    public static <T extends View> T getAdapterView(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
/**
 * 用法：
 if (convertView == null) {
 convertView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_feed_item, parent, false);
 }
 ImageView thumnailView = GetViewHodler.getAdapterView(convertView, R.id.video_thumbnail);
 ImageView avatarView =  GetViewHodler.getAdapterView(convertView, R.id.user_avatar);
 ImageView appIconView = GetViewHodler.getAdapterView(convertView, R.id.app_icon);
 * */