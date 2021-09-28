package com.kstudio.quizapp.chat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kstudio.quizapp.R;

import org.jetbrains.annotations.NotNull;


class ChatAdapter extends ArrayAdapter<ChatMessage> {

    ChatAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_chat_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.circleImageView = convertView.findViewById(R.id.userChatImage);
            viewHolder.username =  convertView.findViewById(R.id.list_item_username);
            viewHolder.message =  convertView.findViewById(R.id.list_item_message);
            viewHolder.text_time = convertView.findViewById(R.id.text_message_time);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ChatMessage item = getItem(position);

        viewHolder.circleImageView.setImageResource(R.drawable.ic_chat_bubble_black_24dp);
        viewHolder.username.setText(item.getUsername());
        viewHolder.message.setText(item.getMessage());
        viewHolder.text_time.setText(item.getComment_time());
        return convertView;

    }

    private static class ViewHolder{
        TextView username;
        TextView message;
        ImageView circleImageView;
        TextView text_time;
    }
}
