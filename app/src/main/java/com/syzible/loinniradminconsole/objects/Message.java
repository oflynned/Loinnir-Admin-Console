package com.syzible.loinniradminconsole.objects;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;
import com.syzible.loinniradminconsole.helpers.EncodingUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by ed on 05/10/2017.
 */

public class Message implements IMessage {
    private String id;
    private User sender;
    private long time;
    private String contents;
    private boolean wasSeen;

    public Message(User sender, JSONObject messageObject) {
        try {
            this.id = messageObject.getJSONObject("_id").getString("$oid");
            this.sender = sender;
            this.time = messageObject.getLong("time");
            this.contents = messageObject.getString("message");
            this.wasSeen = messageObject.getBoolean("was_seen");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Message(String id, User sender, long time, String contents) {
        this(id, sender, time, contents, false);
    }

    public Message(String id, User sender, long time, String contents, boolean wasSeen) {
        this.id = id;
        this.sender = sender;
        this.time = time;
        this.contents = EncodingUtils.encodeText(contents);
        this.wasSeen = wasSeen;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return EncodingUtils.decodeText(contents);
    }

    @Override
    public IUser getUser() {
        return sender;
    }

    @Override
    public Date getCreatedAt() {
        return new Date(time);
    }
}