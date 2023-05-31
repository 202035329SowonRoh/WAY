// FeedAdapter.java

package com.example.way;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<FeedItem> feedItemList;

    public FeedAdapter(List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedItem feedItem = feedItemList.get(position);
        holder.feedImage.setImageURI(feedItem.getImageUri());
        holder.feedTitle.setText(feedItem.getTitle());
        holder.feedContent.setText(feedItem.getContent());
    }


    // 이미지 삭제 기능
    public void deleteImage(int position) {
        if (position >= 0 && position < feedItemList.size()) {
            // 피드 목록에서 특정 피드를 삭제합니다.
            // TODO: 해당 피드에 관련된 이미지와 정보를 삭제합니다.
            feedItemList.remove(position);
            notifyItemRemoved(position); // 어댑터에 아이템 삭제를 알립니다.
            notifyItemRangeChanged(position, feedItemList.size()); // 삭제된 아이템 이후의 아이템 위치를 업데이트합니다.
        }
    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        ImageView feedImage;
        TextView feedTitle;
        TextView feedContent;
        Button deleteButton;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            feedImage = itemView.findViewById(R.id.feedImage);
            feedTitle = itemView.findViewById(R.id.feedTitle);
            feedContent = itemView.findViewById(R.id.feedContent);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            // 삭제 버튼 클릭 이벤트 처리
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        deleteImage(position);
                    }
                }
            });
        }
    }
}
