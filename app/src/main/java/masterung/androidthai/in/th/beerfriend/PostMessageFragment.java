package masterung.androidthai.in.th.beerfriend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PostMessageFragment extends Fragment{

    private String urlAvataString, idString;
    private String tag = "21AugV4";

    public static PostMessageFragment postMessageInstance(String urlAvataString, String idString) {
        PostMessageFragment postMessageFragment = new PostMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Avata", urlAvataString);
        bundle.putString("id", idString);
        postMessageFragment.setArguments(bundle);
        return postMessageFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        urlAvataString = getArguments().getString("Avata");
        idString = getArguments().getString("id");
        Log.d(tag, "Url ==> " + urlAvataString);
        Log.d(tag, "id of Login ==> " + idString);

    }   // Main Method

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_message, container, false);
        return view;
    }
}
