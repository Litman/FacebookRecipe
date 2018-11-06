package ghostl.com.facebookrecipesexample.recipelist.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ghostl.com.facebookrecipesexample.R;
import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.base.ImageLoader;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public RecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.recipeList = recipeList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_recipes, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currenteRecipe = recipeList.get(position);
        imageLoader.load(holder.ivRecipe, currenteRecipe.getImageURL());
        holder.tvRecipeName.setText(currenteRecipe.getTitle());
        holder.ibFavorite.setTag(currenteRecipe.getFavorite());
        if(currenteRecipe.getFavorite()){
            holder.ibFavorite.setImageResource(android.R.drawable.btn_star_big_on);

        }else{
            holder.ibFavorite.setImageResource(android.R.drawable.btn_star_big_off);
        }

        holder.setOnItemClickListener(currenteRecipe, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
    public void setRecipes(List<Recipe> recipes){
        this.recipeList = recipes;
        notifyDataSetChanged();
    }

    public void removeRecipe(Recipe recipe){
        recipeList.remove(recipe);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.ivRecipe)
        ImageView ivRecipe;
        @Bind(R.id.tvRecipeName)
        TextView tvRecipeName;
        @Bind(R.id.ibFavorite)
        ImageButton ibFavorite;
        @Bind(R.id.ibDelete)
        ImageButton ibDelete;
        @Bind(R.id.bfShare)
        ShareButton bfShare;
        @Bind(R.id.bfSend)
        ShareButton bfSend;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Recipe currenteRecipe, final OnItemClickListener onItemClickListener) {

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(currenteRecipe);
                }
            });

            ibFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onDeleteClick(currenteRecipe);
                }
            });

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onDeleteClick(currenteRecipe);
                }
            });

            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(currenteRecipe.getSourceURL()))
                    .build();

            bfShare.setShareContent(content);
            bfSend.setShareContent(content);
        }
    }
}
