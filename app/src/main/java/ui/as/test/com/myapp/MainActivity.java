package ui.as.test.com.myapp;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.data.bean.MyBean;
import com.squareup.picasso.Picasso;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ViewPager pager;
    private List<String> imageUrls;  //图片地址
    private List<View> views;//viewpager的数据源
    private List<MyBean> comments;  //listview的数据源
    private List<String> contents;  //Listview评论内容
    private ArrayAdapter<String> listViewAdapter;

    private int currentPagerIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_vpandlv);

        initView();
        initData();
        initWidget();
        addListner();


    }

    private void initWidget() {
        MyPagetAdapter pagerAdapter = new MyPagetAdapter();
        pager.setAdapter(pagerAdapter);

        contents = new ArrayList<String>();
        for (int i = 0;i<comments.size();i++){
            if(comments.get(i).getPagerFlag() == currentPagerIndex)
                contents.add(comments.get(i).getContent());
        }
        listViewAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contents);
        listView.setAdapter(listViewAdapter);
    }

    private void initData() {
        imageUrls = new ArrayList<String>();
        //虚拟3个数据
        imageUrls.add("http://b.hiphotos.baidu.com/zhidao/pic/item/f636afc379310a5562bec3ceb64543a982261075.jpg");
        imageUrls.add("http://pic.yesky.com/uploadImages/2015/126/48/0S0NK034NU00.jpg");
        imageUrls.add("http://image.tianjimedia.com/uploadImages/2012/233/46/LA0N470016QO.jpg");

        /****************listview data***************/
        //虚拟一些评论数据
        comments = new ArrayList<MyBean>();
        for (int i = 0;i<10;i++){
            MyBean bean = new MyBean(0,"pageflag=0;评论内容："+i);
            comments.add(bean);
        }
        for (int i = 0;i<10;i++){
            MyBean bean = new MyBean(1,"pageflag=1;评论内容："+i);
            comments.add(bean);
        }
        for (int i = 0;i<10;i++){
            MyBean bean = new MyBean(2,"pageflag=2;评论内容："+i);
            comments.add(bean);
        }

        /********************viewpager data***************************/
        ImageView view0 = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_pager,null);

        ImageView view1 = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_pager,null);
        ImageView view2 = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_pager,null);

        views = new ArrayList<View>();
        //用同样的item布局
        views.add(view0);
        views.add(view1);
        views.add(view2);





    }


    private void addListner() {

        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            //翻页完成时触发
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(currentPagerIndex == position)
                    return;
                currentPagerIndex = position;
                contents.clear();
                for (int i = 0;i<comments.size();i++){
                    if(comments.get(i).getPagerFlag() == currentPagerIndex){
                        contents.add(comments.get(i).getContent());
                    }
                }
                listViewAdapter.notifyDataSetChanged();


            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        pager = (ViewPager) findViewById(R.id.pager);
    }



    class MyPagetAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = (ImageView) views.get(position);
            container.addView(view);
            Picasso.with(MainActivity.this).load(imageUrls.get(position)).into(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

}
