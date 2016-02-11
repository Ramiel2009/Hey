package ua.hey.hey;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactsList extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String recipientName;


    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArrayAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static ContactsList newInstance(String param1, String param2) {
        ContactsList fragment = new ContactsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
   /* public void setContext(Context context) {
        this.context = context;
    }

    private Context context;*/

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactsList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, Friends.getFriends());

        // this.setEmptyText("Empty :(");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        // Set the adapter

        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.getPhoneNumber(this.getActivity().getBaseContext());


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String i = mListView.getAdapter().getItem(position).toString();
        System.out.println(i);
        if (null != mListener) {
            this.recipientName = i;
            List recipientPhone = Contacts.phonesArray;
            View v = getActivity().findViewById(android.R.id.content).getRootView();
            Snackbar.make(v, i + " " + recipientPhone.get(position), Snackbar.LENGTH_LONG).show();
            SocketConnection sc = new SocketConnection();
            sc.setAction("send_message");
            sc.setRecPhone(recipientPhone.get(position).toString());
            sc.setContext(this.getActivity().getBaseContext());
            sc.execute();
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
}
