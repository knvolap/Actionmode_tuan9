package volap.example.actionmode_tuan9;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import volap.example.actionmode_tuan9.R;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ListViewBaseAdapter adapter;
    ArrayList<ListViewBean> arr_bean;
    Vector<Integer> index=new Vector<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trần Võ Lập");
        Drawable drawable= getResources().getDrawable(R.drawable.ic_group);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        //Listview
        lv = (ListView) findViewById(R.id.list_post);
        arr_bean=new ArrayList<ListViewBean>();
        arr_bean.add(new ListViewBean(R.drawable.ic_soccer, "CLB Bóng đá","Hoạt động vào sáng thứ 3 5 7 hằng tuần"));
        arr_bean.add(new ListViewBean(R.drawable.ic_basketball, "CLB Bóng rổ","Hoạt động vào chiều thứ 3 5 7 hằng tuần"));
        arr_bean.add(new ListViewBean(R.drawable.ic_football,"CLB Bóng ném","Hoạt động vào tối thứ 3 5 7 hằng tuần"));
        arr_bean.add(new ListViewBean(R.drawable.ic_kabaddi, "CLB Karate","Hoạt động vào sáng thứ 2 4 6 hằng tuần"));
        arr_bean.add(new ListViewBean(R.drawable.ic_volleyball, "CLB Bóng chuyền","Hoạt động vào tối thứ 2 4 6 hằng tuần"));
        arr_bean.add(new ListViewBean(R.drawable.ic_videogame, "CLB eSpost","Hoạt động vào thứ 7 và chủ nhật "));
        adapter=new ListViewBaseAdapter(arr_bean,this);
        lv.setAdapter((ListAdapter) adapter);
        registerForContextMenu(lv);
        index.add(-1);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                try {
                    //chuẩn bị danh sách các phần tử được chọn
                    if (index.get(0) == -1)
                        index.set(0, position);
                    else {
                        int yn = 0, vt = -1;
                        for (int i = 0; i < index.size(); ++i) {
                            if (index.get(i) == position) {
                                yn = 1;
                                vt = i;
                                break;
                            }
                        }
                        //nếu phần tử đã chọn trc đó thì xóa khỏi danh sách
                        if (yn == 1) index.remove(vt);
                        else index.add(position);
                    }
                }
                catch (Exception e){
                    index.add(position);
                }

                //thiết lập lại màu cho toàn bộ
                for (int i = 0; i < lv.getChildCount(); i++) {
                    lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                //thiết lập màu cho các phần tử đc chọn
                for (int i = 0; i < lv.getChildCount(); i++) {
                    for(int j=0;j<index.size();++j)
                        if(index.get(j) == i){
                            lv.getChildAt(i).setBackgroundColor(Color.GRAY);
                        }
                }
                mode.setTitle(index.size()+" đã chọn");

            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case android.R.id.home:
                        index.clear();
                        //thiết lập lại màu cho toàn bộ
                        for (int i = 0; i < lv.getChildCount(); i++) {
                            lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        return true;
                    case R.id.them:
                        Toast.makeText(MainActivity.this,"Thêm",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.sua:
                        Toast.makeText(MainActivity.this,"Sửa",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.xoa:
                        try {
                            Collections.sort(index);
                            for (int i = index.size()-1; i >=0 ; --i) {
                                arr_bean.remove(Integer.parseInt(index.get(i).toString()));
                                adapter.notifyDataSetChanged();
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this,"Bạn chưa chọn dòng nào cả",Toast.LENGTH_SHORT).show();
                        }

                        index.clear();
                        //thiết lập lại màu cho toàn bộ
                        for (int i = 0; i < lv.getChildCount(); i++) {
                            lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        Log.d("Xoa mang",Integer.toString(index.size()));
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });
    }


}