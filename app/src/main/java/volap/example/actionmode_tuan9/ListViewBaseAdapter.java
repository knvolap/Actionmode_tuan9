package volap.example.actionmode_tuan9;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import volap.example.actionmode_tuan9.R;

public class ListViewBaseAdapter  extends BaseAdapter{
    public ArrayList<ListViewBean> arrayingListener;
    Context mContext;

    public ListViewBaseAdapter(ArrayList<ListViewBean> mListenerList, Context context) {
        mContext = context;
        this.arrayingListener = new ArrayList<ListViewBean>();
        this.arrayingListener =mListenerList;
    }

    public void notifyDataSetChanged() {
    }

    public class ViewHolder {
        ImageView mItemPic;
        TextView mLangName,moTa;
    }

    @Override
    public int getCount() {
        return arrayingListener.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayingListener.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.mItemPic = (ImageView) view.findViewById(R.id.im_test);
            holder.mLangName=(TextView)view.findViewById(R.id.tv_langName);
            holder.moTa=(TextView)view.findViewById(R.id.mota);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            int image = arrayingListener.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mLangName.setText(arrayingListener.get(position).getLangName());
            holder.moTa.setText(arrayingListener.get(position).getMoTa());
        }catch (Exception ex){
        }
        return view;
    }

}
