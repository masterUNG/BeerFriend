package masterung.androidthai.in.th.beerfriend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFriendFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create RecyclerView
        createRecyclerView();

    }   // Main Method

    private void createRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewFriend);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        MyConstant myConstant = new MyConstant();

        try {

            ReadAllData readAllData = new ReadAllData(getActivity());
            readAllData.execute(myConstant.getUrlGetAllUserString());
            String resultString = readAllData.get();
            Log.d("22AugV1", "Result JSoN ==> " + resultString);

            String[] columnStrings = myConstant.getColumnUserStrings();
            ArrayList<String> avataStringArrayList = new ArrayList<>();
            ArrayList<String> nameStringArrayList = new ArrayList<>();
            ArrayList<String> postStringArrayList = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(resultString);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                avataStringArrayList.add(jsonObject.getString(columnStrings[4]));
                nameStringArrayList.add(jsonObject.getString(columnStrings[1]));
                postStringArrayList.add(findLastPost(jsonObject.getString(columnStrings[5])));

                Log.d("22AugV2", "avata ==> " + avataStringArrayList.toString());
                Log.d("22AugV2", "name ==> " + nameStringArrayList.toString());
                Log.d("22AugV2", "post ==> " + postStringArrayList.toString());


            }   // for

            FriendAdapter friendAdapter = new FriendAdapter(getActivity(),
                    avataStringArrayList, nameStringArrayList, postStringArrayList);
            recyclerView.setAdapter(friendAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String findLastPost(String currentString) {

        String resultString = null;

        resultString = currentString.substring(1, currentString.length() - 1);

        String[] strings = resultString.split(",");

        return strings[strings.length-1];
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_friend, container, false);
        return view;
    }
}
