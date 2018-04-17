package com.example.lancer.wechat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lancer.wechat.R;
import com.example.lancer.wechat.util.Contact;
import com.example.lancer.wechat.util.ContactComparator;
import com.example.lancer.wechat.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Lancer on 2018/4/16.
 */
public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mContactNames; // 联系人名称字符串数组
    private List<String> mContactList; // 联系人名称List（转换成拼音）
    public static List<Contact> resultList; // 最终结果（包含分组的字母）
    private List<String> characterList; // 字母List

    public enum ITEM_TYPE {
        ITEM_TYPE_CHARACTER,
        ITEM_TYPE_CONTACT
    }

    public ContactAdapter(Context context, String[] contactNames) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mContactNames = contactNames;

        handleContact();
    }

    private void handleContact() {
        mContactList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < mContactNames.length; i++) {
            String pinyin = Utils.getPingYin(mContactNames[i]);
            map.put(pinyin, mContactNames[i]);
            mContactList.add(pinyin);
        }
        Collections.sort(mContactList, new ContactComparator());

        resultList = new ArrayList<>();
        characterList = new ArrayList<>();

        for (int i = 0; i < mContactList.size(); i++) {
            String name = mContactList.get(i);
            String character = (name.charAt(0) + "").toUpperCase(Locale.ENGLISH);
            if (!characterList.contains(character)) {
                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                    characterList.add(character);
                    resultList.add(new Contact(character, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                } else {
                    if (!characterList.contains("#")) {
                        characterList.add("#");
                        resultList.add(new Contact("#", ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                    }
                }
            }

            resultList.add(new Contact(map.get(name), ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()) {
            View view = View.inflate(mContext, R.layout.item_character, null);
            CharacterHolder characterHolder = new CharacterHolder(view);
          //  view.setOnClickListener(this);
            return characterHolder;
        } else {
            View view = View.inflate(mContext, R.layout.item_contact, null);
            ContactHolder holder = new ContactHolder(view);
            view.setOnClickListener(this);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterHolder) {
            CharacterHolder holder1 = (CharacterHolder) holder;
            holder1.mTextView.setText(resultList.get(position).getmName());
        } else if (holder instanceof ContactHolder) {
            ContactHolder holder1 = (ContactHolder) holder;
            holder1.mTextView.setText(resultList.get(position).getmName());
            holder1.itemView.setTag(position);
        /*    ((ContactHolder) holder).mTextView.setText(resultList.get(position).getmName());*/
        }
    }

    @Override
    public int getItemViewType(int position) {
        return resultList.get(position).getmType();
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    public class CharacterHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        CharacterHolder(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.character);
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ContactHolder(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.contact_name);
        }
    }

    public int getScrollPosition(String character) {
        if (characterList.contains(character)) {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).getmName().equals(character)) {
                    return i;
                }
            }
        }

        return -1; // -1不会滑动
    }

    private OnItemClickLinter mOnItemClickLinter = null;

    public static interface OnItemClickLinter {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickLinter != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickLinter.onItemClick(v, (int) v.getTag());
        }
    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法（）：
    public void setOnItemClickListener(OnItemClickLinter listener) {
        this.mOnItemClickLinter = listener;
    }
}
