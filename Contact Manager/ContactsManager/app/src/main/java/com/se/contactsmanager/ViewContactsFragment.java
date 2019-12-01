package com.se.contactsmanager;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import com.se.contactsmanager.Utils.ContactListAdapter;
import com.se.contactsmanager.Utils.DatabaseHelper;
import com.se.contactsmanager.models.Contact;

public class ViewContactsFragment extends Fragment {

    private static final String TAG = "ViewContactsFragment";
    private String testImageMale = "https://icon-library.net//images/default-profile-icon/default-profile-icon-18.jpg";
    private String testImageFemale = "http://www.herzing.ca/wp-content/uploads/2017/03/testimonial-thumbnail-default-female-2-150x150.png";



    public interface OnContactSelectedListener{
        public void OnContactSelected(Contact con);
    }
    OnContactSelectedListener mContactListener;

    public interface OnAddContactListener{
        public void onAddContact();
    }
    OnAddContactListener mOnAddContact;


    //variables and widgets
    private static final int STANDARD_APPBAR = 0;
    private static final int SEARCH_APPBAR = 1;
    private int mAppBarState;


    private AppBarLayout viewContactsBar, searchBar;
    private ContactListAdapter adapter;
    private ListView contactsList;
    private EditText mSearchContacts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewcontacts, container, false);
        viewContactsBar = (AppBarLayout) view.findViewById(R.id.viewContactsToolbar);
        searchBar = (AppBarLayout) view.findViewById(R.id.searchToolbar);
        contactsList = (ListView) view.findViewById(R.id.contactsList);
        mSearchContacts = (EditText) view.findViewById(R.id.etSearchContacts);
        Log.d(TAG, "onCreateView: started.");


        setAppBarState(STANDARD_APPBAR);

        setupContactsManager();

        // navigate to add contacts fragment
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabAddContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked fab.");
                mOnAddContact.onAddContact();
            }
        });

        ImageView ivSearchContact = (ImageView) view.findViewById(R.id.ivSearchIcon);
        ivSearchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked search icon.");
                toggleToolBarState();
            }
        });

        ImageView ivBackArrow = (ImageView) view.findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                toggleToolBarState();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mContactListener = (OnContactSelectedListener) getActivity();
            mOnAddContact = (OnAddContactListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
        }
    }

    //
    private void setupContactsManager(){
        final ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Smith", "(232) 343-2324", "Mobile","jsmith@hotmail.com", testImageMale));
        contacts.add(new Contact("Harry West", "(346) 643-3452", "Mobile","harryw33@gmail.com", testImageMale));
        contacts.add(new Contact("Angela Garland", "(643) 543-2467", "Work","garlandiscool@gmail.com", testImageFemale));
        contacts.add(new Contact("Dominique Wilkins", "(844) 085-2346", "Mobile","dwilk392@yahoo.com", testImageMale));
        contacts.add(new Contact("Jerry Stone", "(182) 342-5235", "Mobile","j3stone@gmail.com", testImageMale));
        contacts.add(new Contact("Mitch Robinson", "(865) 086-9564", "Work","robinson3298@yahoo.com", testImageMale));
        contacts.add(new Contact("Scott Terry", "(927) 493-9744", "Work","scotty493@gmail.com", testImageMale));
        contacts.add(new Contact("Jessica Glen", "(038) 937-0376", "Work","jdg093@hotmail.com", testImageFemale));
        contacts.add(new Contact("Mary Anthony", "(973) 408-6372", "Work","mxa493033@utdallas.edu", testImageFemale));
        contacts.add(new Contact("Emily Cross", "(638) 018-3917", "Mobile","emilycross@gmail.com", testImageFemale));
        contacts.add(new Contact("Jacob Armstrong", "(673) 163-1728", "Work","armstrong39482@yahoo.com", testImageMale));
        contacts.add(new Contact("Isaac Feller", "(127) 097-8963", "Mobile","ifeller392@gmail.com", testImageMale));
        contacts.add(new Contact("Robert Ramsey", "(536) 297-2583", "Work","robram3938@hotmail.com", testImageMale));
        contacts.add(new Contact("Dave Chapelle", "(903) 321-8927", "Mobile","chapelle9289@gmail.com", testImageMale));
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = databaseHelper.getAllContacts();

        //iterate through all the rows contained in the database
        if(!cursor.moveToNext()){
            Toast.makeText(getActivity(), "There are no contacts to show", Toast.LENGTH_SHORT).show();
        }
        while(cursor.moveToNext()){
            contacts.add(new Contact(
                    cursor.getString(1),//name
                    cursor.getString(2),//phone number
                    cursor.getString(3),//device
                    cursor.getString(4),//email
                    cursor.getString(5)//profile image uri
            ));
        }

        //Log.d(TAG, "setupContactsManager: image url: " + contacts.get(0).getProfileImage());

        //sort the arraylist based on the contact name
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        adapter = new ContactListAdapter(getActivity(), R.layout.layout_contactslistitem, contacts, "");


        mSearchContacts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text = mSearchContacts.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contactsList.setAdapter(adapter);

        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onClick: navigating to " + getString(R.string.contact_fragment));

                //pass the contact to the interface and send it to MainActivity
                mContactListener.OnContactSelected(contacts.get(position));
            }
        });


    }

    /**
     * Initiates the appbar state toggle
     */
    private void toggleToolBarState() {
        Log.d(TAG, "toggleToolBarState: toggling AppBarState.");
        if(mAppBarState == STANDARD_APPBAR){
            setAppBarState(SEARCH_APPBAR);
        }else{
            setAppBarState(STANDARD_APPBAR);
        }
    }

    /**
     * Sets the appbar state for either the search 'mode' or 'standard' mode
     * @param state
     */
    private void setAppBarState(int state) {
        Log.d(TAG, "setAppBarState: changing app bar state to: " + state);

        mAppBarState = state;

        if(mAppBarState == STANDARD_APPBAR){
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);

            //hide the keyboard
            View view = getView();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try{
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }catch (NullPointerException e){
                Log.d(TAG, "setAppBarState: NullPointerException: " + e.getMessage());
            }
        }

        else if(mAppBarState == SEARCH_APPBAR){
            viewContactsBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);

            //open the keyboard
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(STANDARD_APPBAR);
    }
}


















