package com.android.book2joy.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.book2joy.R;
import com.google.firebase.firestore.auth.User;

public class InfoFragment extends Fragment {

    private static final String TAG = "InfoFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ProgressBar progressBar_cyclic;

    LinearLayout personalinfo, booking_details, review;
    Button btnsignout;
    TextView personalinfobtn, booking_details_btn, reviewbtn;

    TextView name_user, des_user, phone_user, email_user, address_user, sex_user, job_user, job_user_main, workplace_user;

    TextView tv_edit_contact, tv_edit_about_me, tv_edit_job;

    TextView percent_completed_user;

    ImageView btnLogoout, topAppBar, show_img_info;

    User user;

//    private ReviewAdapter adapter;
//    private ArrayList<Review> reviews = new ArrayList<>();
    private RecyclerView rc_hotel_review;

    private FragmentActivity myContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        personalinfo = rootView.findViewById(R.id.personalinfo);
        booking_details = rootView.findViewById(R.id.booking_details);
        review = rootView.findViewById(R.id.review);
        personalinfobtn = rootView.findViewById(R.id.personalinfobtn);
        booking_details_btn = rootView.findViewById(R.id.booking_details_btn);
        reviewbtn = rootView.findViewById(R.id.reviewbtn);
        /*making personal info visible*/
        personalinfo.setVisibility(View.VISIBLE);
        booking_details.setVisibility(View.GONE);
        review.setVisibility(View.GONE);

        phone_user = rootView.findViewById(R.id.phone_user);
        email_user = rootView.findViewById(R.id.email_user);
        address_user = rootView.findViewById(R.id.address_user);
        sex_user = rootView.findViewById(R.id.sex_user);

        tv_edit_contact = rootView.findViewById(R.id.tv_edit_contact);





//        rc_hotel_review = rootView.findViewById(R.id.rc_review_hotel);
//
//        rc_hotel_review.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        rc_hotel_review.setHasFixedSize(true);
//        rc_hotel_review.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        // Dialog
//        tv_edit_contact.setOnClickListener((View v) -> {
//            ContactDialog contact_dialog = new ContactDialog();
//            contact_dialog.show(getFragmentManager(), "ContactDialog");
//        });
//
//        tv_edit_about_me.setOnClickListener((View v) -> {
//            AboutMeDialog about_dialog = new AboutMeDialog();
//            about_dialog.show(getFragmentManager(), "AboutMeDialog");
//        });
//
//        tv_edit_job.setOnClickListener((View v) -> {
//            JobDetailDialog job_dialog = new JobDetailDialog();
//            job_dialog.show(getFragmentManager(), "JobDetailDialog");
//        });
//
//        show_img_info.setOnClickListener((View v) -> {
//            ImageDialog img_dialog = new ImageDialog();
//            img_dialog.show(getFragmentManager(), "ImageDialog");
//        });

        personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.VISIBLE);
                booking_details.setVisibility(View.GONE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.blue));
                booking_details_btn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        booking_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);
                booking_details.setVisibility(View.VISIBLE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                booking_details_btn.setTextColor(getResources().getColor(R.color.blue));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);
                booking_details.setVisibility(View.GONE);
                review.setVisibility(View.VISIBLE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                booking_details_btn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.blue));

            }
        });

//        btnLogoout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent i = new Intent(getActivity(), Login_Signin.class);
//                startActivity(i);
//                getActivity().finish();
//            }
//        });

//        topAppBar.setOnClickListener((View v) -> {
//        });
//
//        progressBar_cyclic.setVisibility(View.VISIBLE);
//        DatabaseReference UserData = FirebaseDatabase.getInstance().getReference("Users");
//
////      Load user from id
//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        UserData.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                user = dataSnapshot.getValue(User.class);
////              set data in TextView
//                if (!user.getLove().equals("")) {
//                    percent_completed_user.setText("100%");
//                } else {
//                    percent_completed_user.setText("97%");
//                }
//                name_user.setText(user.getName());
//                des_user.setText(user.getLove());
//                email_user.setText(user.getEmail());
//                phone_user.setText(user.getPhone());
//                sex_user.setText(user.getSex());
//                address_user.setText(user.getAddress());
//                job_user.setText(user.getJob());
//                job_user_main.setText(user.getJob());
//                workplace_user.setText(user.getWorkplace());
//
//                if (user.getReview().size() != 0) {
//                    reviews.addAll(user.getReview());
//                }
//                adapter = new ReviewAdapter(getActivity(), reviews);
//                adapter.notifyDataSetChanged();
//                rc_hotel_review.setAdapter(adapter);
//
//                // Convert base 64 string to bitmap
//                byte[] decodedString = Base64.decode(user.getImage(), Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                Bitmap bMapScaled = Bitmap.createScaledBitmap(decodedByte, 800, 1300, true);
//                show_img_info.setImageBitmap(bMapScaled);
//
//                progressBar_cyclic.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                Log.e("test", "Failed to read value.");
//            }
//        });

    }
}