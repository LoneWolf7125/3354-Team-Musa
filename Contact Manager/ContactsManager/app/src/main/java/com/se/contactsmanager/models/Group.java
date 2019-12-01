package com.se.contactsmanager.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable {

    private Contact[] contacts; //array of contacts for each group
    private String groupName;
    private int groupSize;

    //constructor for group
    public Group(Contact[] contacts, String groupName, int groupSize)
    {
        this.contacts = contacts;
        this.groupName = groupName;
        this.groupSize = groupSize;
    }

    protected Group(Parcel in)
    {
        contacts = in.createTypedArray(Contact.CREATOR);
        groupName = in.readString();
        groupSize = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeTypedArray(contacts,0);
        dest.writeString(groupName);
        dest.writeInt(groupSize);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public Contact[] getContacts() { return contacts; }
    public String getGroupName() { return groupName; }
    public int getGroupSize() { return groupSize; }

    public void setContacts(Contact[] contacts){ this.contacts = contacts; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public void setGroupSize(){ this.groupSize = groupSize; }

    @Override
    public String toString()
    {
        return "Group{" +
                "group name='" + groupName + '\'' +
                ", group size='" + groupSize + '\'' +
                '}';
    }
}
