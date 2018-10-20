package ghostl.com.facebookrecipesexample.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

import ghostl.com.facebookrecipesexample.libs.base.ImageLoader;

public class GlideImageLoader implements ImageLoader {

    private RequestListener onFinishedLoadingListener;
    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if(onFinishedLoadingListener != null){
            glideRequestManager.load(URL)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.centerCrop()
                    .listener(onFinishedLoadingListener)
                    .into(imageView);

        }else{
            glideRequestManager.load(URL)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.centerCrop()
                    .into(imageView);

        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        if(listener instanceof RequestListener){
            this.onFinishedLoadingListener = (RequestListener) listener;
        }

    }
}
