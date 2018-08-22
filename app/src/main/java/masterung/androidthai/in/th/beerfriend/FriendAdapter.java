package masterung.androidthai.in.th.beerfriend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>{

    private Context context;
    private ArrayList<String> avataStringArrayList, nameStringArrayList, postStringArrayList;
    private LayoutInflater layoutInflater;

    public FriendAdapter(Context context,
                         ArrayList<String> avataStringArrayList,
                         ArrayList<String> nameStringArrayList,
                         ArrayList<String> postStringArrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.avataStringArrayList = avataStringArrayList;
        this.nameStringArrayList = nameStringArrayList;
        this.postStringArrayList = postStringArrayList;
    }   // Constructor

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recycler_view_friend, viewGroup, false);
        FriendViewHolder friendViewHolder = new FriendViewHolder(view);

        return friendViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder friendViewHolder, int i) {

        String avataString = avataStringArrayList.get(i);
        String nameString = nameStringArrayList.get(i);
        String postString = postStringArrayList.get(i);

        friendViewHolder.nameTextView.setText(nameString);
        friendViewHolder.postTextView.setText(postString);

        Picasso.get()
                .load(avataString)
                .resize(100, 100)
                .into(friendViewHolder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return nameStringArrayList.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView circleImageView;
        private TextView nameTextView, postTextView;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circleFriend);
            nameTextView = itemView.findViewById(R.id.txtName);
            postTextView = itemView.findViewById(R.id.txtPost);

        }
    }   // Second Class

}   // Main Class
