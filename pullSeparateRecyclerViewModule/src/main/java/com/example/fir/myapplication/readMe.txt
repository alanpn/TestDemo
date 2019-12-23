

https://github.com/tianyu704/PullSeparateRecyclerView

银行卡列表展示页

ScrollingActivity 跳转到 DetailActivity

共享元素
    从 ScrollingActivity 的 Adapter item_view.xml 和  DetailActivity 的 activity_detail.xml 的
        CardView 设置 android:transitionName="card"

    DetailActivity.show(ScrollingActivity.this, cardView, color, name);

    public static void show(AppCompatActivity activity, View view, int color, String name) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("color", color);
        intent.putExtra("name", name);
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, view, "card").toBundle());
    }

如果有多个进行跳转 ActivityOptions.makeSceneTransitionAnimation(activity, Pair.create(view, "card"), Pair.create(view, "card1"));