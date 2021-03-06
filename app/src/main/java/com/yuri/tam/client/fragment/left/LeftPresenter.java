package com.yuri.tam.client.fragment.left;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.yuri.tam.R;
import com.yuri.tam.base.App;
import com.yuri.tam.base.BasePresenter;
import com.yuri.tam.common.constant.SysConstant;
import com.yuri.tam.core.api.IDataSource;

import static com.common.utils.Utils.checkNotNull;

/**
 * 左侧菜单操作
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年11月29日
 */
public class LeftPresenter extends BasePresenter implements LeftContract.Presenter {

    private final LeftContract.View mView;

    public LeftPresenter(LeftContract.View view, IDataSource repository) {
        super(view, repository);
        mView = checkNotNull(view);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void checkUpdate(FragmentActivity activity) {
        postMainThread(() -> mView.showIsNewestVersion());
        // FIXME: 2017/12/6 需要用到的升级功能的，做相应修改即可使用
//        new UpdateHelper(activity)
//                .setCheckUrl("http://jcodecraeer.com/update.php")
//                .setIsAutoInstall(true)
//                .setCheckListener(new OnCheckListener() {
//
//                    @Override
//                    public void onStart() {
//                        postMainThread(() -> mView.setCheckUpdateIndicator(true));
//                    }
//
//                    @Override
//                    public void onResult(boolean hasNewVersion) {
//                        postMainThread(() -> mView.setCheckUpdateIndicator(false));
//                        if (!hasNewVersion){
//                            postMainThread(() -> mView.showIsNewestVersion());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        postMainThread(() -> mView.setCheckUpdateIndicator(false));
//
//                    }
//                })
//                .build();
    }

    @Override
    public void login2Setting(String password) {
        if (TextUtils.isEmpty(password)) {
            postMainThread(() -> mView.showEmpty());
            return;
        }
        mLog.debug("用户输入密码为：{}", password);
        if (!SysConstant.DEFAULT_PASSWORD.equals(password)){
            postMainThread(() -> mView.showErr(App.sContext.getString(R.string.password_error)));
            return;
        }
        postMainThread(() -> mView.intent2Setting());
    }

}
