# Parallax Recycler Adapter

A simple RecyclerView adapter that enable the RecyclerView to add a top header, which is scrolled with a parallax effect.

The usage is pretty simple. Just get the instance of the RV inserted in your layout and attach to it the adapter, togheter with the header's layout resource Id, in the Adapter's constructor.

After this, you should override the abstract methods, that handle the creation of new items and the bind of a model instance to specific viewholder, like it happens in a default RV's adapter.

The method onBindNonHeaderViewHolder also returns the model's istance associated with the Adapter directly as a parameter.

```sh
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

ParallaxHeaderRecyclerAdapter headerRecyclerAdapter = new ParallaxHeaderRecyclerAdapter<CustomClass>(recyclerView, R.layout.layout_header) {
    @Override
    protected RecyclerView.ViewHolder onCreateNonHeaderViewHolder(ViewGroup viewGroup, int viewType) {
        return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false));
    }

    @Override
    protected void onBindNonHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position, CustomClass item) { 
      // do some layout stuff
      }
    };
        
  headerRecyclerAdapter.append(new CustomClass());
  recyclerView.setAdapter(headerRecyclerAdapter);
```

