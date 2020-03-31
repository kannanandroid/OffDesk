package com.ifazig.optdesk.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.activity.CalanderActivityNew;
import com.ifazig.optdesk.activity.DecoderActivity;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.LoginApiResponseModel;
import com.ifazig.optdesk.api_model.SettingsDetailsApiResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.databinding.HomeFragmentBinding;
import com.ifazig.optdesk.spinnerdialog.OnSpinerItemClick;
import com.ifazig.optdesk.spinnerdialog.SpinnerDialog;
import com.ifazig.optdesk.spinnerdialog.SpinnerModel;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.ifazig.optdesk.activity.LoginActivity.companyDetails;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragmentNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragmentNew extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AppCompatActivity activity;
    FragmentManager fragmentManager;
    HomeFragmentBinding binding;
    String companyID = "";
    String locationID = "";
    String buildingID = "";
    String floorID = "";
    String wingID = "";
    private List<LoginApiResponseModel.BuildingDetail> buildingDetails;
    private List<LoginApiResponseModel.FloorDetail> floorDetails;
    private List<LoginApiResponseModel.WingsDetail> wingDetails;
    public List<LoginApiResponseModel.LocationDetail> locationDetails;
    public List<LoginApiResponseModel.CompanyDetail> items;

    private OnFragmentInteractionListener mListener;

    public HomeFragmentNew() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardListingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragmentNew newInstance(String param1, String param2) {
        HomeFragmentNew fragment = new HomeFragmentNew();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment_new, container, false);
        View view = binding.getRoot();
        CommonFunctions.getInstance().drawLayout((AppCompatActivity) getActivity(), binding.toolbar);
        fragmentManager = getFragmentManager();
        initView();
        return view;
    }

    private void initView() {
        binding.tlbarText.setText(LanguageConstants.home);
        binding.tvsubmit.setText(LanguageConstants.continuetxt);
        binding.tvLocation.setHint(LanguageConstants.locationtxt);
        binding.tvBuilding.setHint(LanguageConstants.buildingtxt);
        binding.tvFloor.setHint(LanguageConstants.floortxt);
        binding.tvWing.setHint(LanguageConstants.wingtxt);

        binding.ivQr.setOnClickListener(this);
        binding.llCompany.setOnClickListener(this);
        binding.llLocation.setOnClickListener(this);
        binding.llBuilding.setOnClickListener(this);
        binding.llFloor.setOnClickListener(this);
        binding.llWing.setOnClickListener(this);
        binding.rlSubmit.setOnClickListener(this);

        if (companyDetails != null && companyDetails.size() > 0) {
            binding.tvCompany.setText(companyDetails.get(0).getCompanyName());
            companyID = String.valueOf(companyDetails.get(0).getCompanyID());
            getAllDetails();
        } else {
            String ITEMS = SessionManager.getInstance().getFromPreference(SharedPrefConstants.COMPANYDETAILS);
            if (ITEMS != null) {
                Gson gson = new Gson();
                Type listOfObjects2 = new TypeToken<List<LoginApiResponseModel.CompanyDetail>>() {
                }.getType();
                companyDetails = gson.fromJson(ITEMS, listOfObjects2);
                if (items != null && items.size() > 0) {
                    binding.tvCompany.setText(items.get(0).getCompanyName());
                    companyID = String.valueOf(items.get(0).getCompanyID());
                    getAllDetails();

                }
            }
        }
    }

    private void getAllDetails() {
        CommonApiCalls.getInstance().getLoginDetails(getActivity(), "0",
                "0", SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID), "0", SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID), "0", "0", "0", "0", new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        LoginApiResponseModel body = (LoginApiResponseModel) object;
                        LoginApiResponseModel.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            if (body.getReturnData() != null) {
                                locationDetails = data.getLocationDetails();
                                buildingDetails = data.getBuildingDetails();
                                floorDetails = data.getFloorDetails();
                                wingDetails = data.getWingsDetails();
                                if (locationDetails != null && locationDetails.size() > 0) {
                                    binding.tvLocation.setText(locationDetails.get(0).getLocationName());
                                    locationID = String.valueOf(locationDetails.get(0).getLocationID());
                                }
                                if (buildingDetails != null && buildingDetails.size() > 0) {
                                    binding.tvBuilding.setText(buildingDetails.get(0).getBuildingName());
                                    buildingID = String.valueOf(buildingDetails.get(0).getBuildingID());
                                }
                                if (floorDetails != null && floorDetails.size() > 0) {
                                    binding.tvFloor.setText(floorDetails.get(0).getFloorName());
                                    floorID = String.valueOf(floorDetails.get(0).getFloorID());
                                }
                                if (wingDetails != null && wingDetails.size() > 0) {
                                    binding.tvWing.setText(wingDetails.get(0).getWingName());
                                    wingID = String.valueOf(wingDetails.get(0).getWingID());
                                }

                            } else {
                                CommonFunctions.getInstance().successResponseToast(getActivity(), LanguageConstants.dataEmpty);
                            }
                        } else {
                            CommonFunctions.getInstance().successResponseToast(getActivity(), body.getMessage());
                        }


                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }

        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llCompany:
                if (companyDetails == null) {
                    CommonFunctions.getInstance().validationInfoError(getActivity(), LanguageConstants.noLocation);
                    return;
                } else {
                    final ArrayList<SpinnerModel> mSpinnerModels = new ArrayList<>();
                    SpinnerModel mSpinnerModel;
                    for (int count = 0; count < companyDetails.size(); count++) {
                        mSpinnerModel = new SpinnerModel();
                        mSpinnerModel.setId(String.valueOf(companyDetails.get(count).getCompanyID()));
                        mSpinnerModel.setName(companyDetails.get(count).getCompanyName());
                        mSpinnerModels.add(mSpinnerModel);
                    }
                    SpinnerDialog spinnerDialog;
                    spinnerDialog = new SpinnerDialog(getActivity(), mSpinnerModels, LanguageConstants.company);
                    spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvCompany.setText(mSpinnerModels.get(position).getName());
                            binding.tvCompany.setTag(mSpinnerModels.get(position).getId());
                            companyID = mSpinnerModels.get(position).getId();
                            getLocationDetailsApi("0", "0", SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID), companyID, SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID), "0", "0", "0", "0");
                        }
                    });
                    spinnerDialog.showSpinerDialog();
                }
                break;
            case R.id.llLocation:
                if (locationDetails == null) {
                    CommonFunctions.getInstance().validationInfoError(getActivity(), LanguageConstants.noLocation);
                    return;
                } else {
                    final ArrayList<SpinnerModel> mSpinnerModels = new ArrayList<>();
                    SpinnerModel mSpinnerModel;
                    for (int count = 0; count < locationDetails.size(); count++) {
                        mSpinnerModel = new SpinnerModel();
                        mSpinnerModel.setId(String.valueOf(locationDetails.get(count).getLocationID()));
                        mSpinnerModel.setName(locationDetails.get(count).getLocationName());
                        mSpinnerModels.add(mSpinnerModel);
                    }
                    SpinnerDialog spinnerDialog;
                    spinnerDialog = new SpinnerDialog(getActivity(), mSpinnerModels, LanguageConstants.location);
                    spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvLocation.setText(mSpinnerModels.get(position).getName());
                            binding.tvLocation.setTag(mSpinnerModels.get(position).getId());
                            locationID = mSpinnerModels.get(position).getId();
                            getbuildDetailsApi("0", "0", SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID), companyID, SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID), locationID, "0", "0", "0");
                        }
                    });
                    spinnerDialog.showSpinerDialog();
                }
                break;
            case R.id.llBuilding:
                if (buildingDetails == null) {
                    CommonFunctions.getInstance().validationInfoError(getActivity(), LanguageConstants.nobuilding);
                    return;
                } else {

                    final ArrayList<SpinnerModel> mSpinnerModelsbuild = new ArrayList<>();
                    SpinnerModel mSpinnerModelbuild;
                    for (int count = 0; count < buildingDetails.size(); count++) {
                        mSpinnerModelbuild = new SpinnerModel();
                        mSpinnerModelbuild.setId(String.valueOf(buildingDetails.get(count).getBuildingID()));
                        mSpinnerModelbuild.setName(buildingDetails.get(count).getBuildingName());
                        mSpinnerModelsbuild.add(mSpinnerModelbuild);
                    }
                    SpinnerDialog spinnerDialogbuild;
                    spinnerDialogbuild = new SpinnerDialog(getActivity(), mSpinnerModelsbuild, LanguageConstants.building);
                    spinnerDialogbuild.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvBuilding.setText(mSpinnerModelsbuild.get(position).getName());
                            binding.tvBuilding.setTag(mSpinnerModelsbuild.get(position).getId());
                            buildingID = mSpinnerModelsbuild.get(position).getId();
                            getFloorDetailsApi("0", "0", SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID), companyID,
                                    SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID), "0", buildingID, "0", "0");
                        }
                    });
                    spinnerDialogbuild.showSpinerDialog();
                }
                break;
            case R.id.llFloor:
                if (floorDetails == null) {
                    CommonFunctions.getInstance().validationInfoError(getActivity(), LanguageConstants.nofloor);
                    return;
                } else {
                    final ArrayList<SpinnerModel> mSpinnerModelsfloor = new ArrayList<>();
                    SpinnerModel mSpinnerModelfloor;
                    for (int count = 0; count < floorDetails.size(); count++) {
                        mSpinnerModelfloor = new SpinnerModel();
                        mSpinnerModelfloor.setId(String.valueOf(floorDetails.get(count).getFloorID()));
                        mSpinnerModelfloor.setName(floorDetails.get(count).getFloorName());
                        mSpinnerModelsfloor.add(mSpinnerModelfloor);
                    }
                    SpinnerDialog spinnerDialogfloor;
                    spinnerDialogfloor = new SpinnerDialog(getActivity(), mSpinnerModelsfloor, LanguageConstants.floor);
                    spinnerDialogfloor.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvFloor.setText(mSpinnerModelsfloor.get(position).getName());
                            binding.tvFloor.setTag(mSpinnerModelsfloor.get(position).getId());
                            floorID = mSpinnerModelsfloor.get(position).getId();
                            getWingDetailsApi("0", "0", SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID), companyID,
                                    SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID), "0", buildingID, floorID, "0");
                        }
                    });
                    spinnerDialogfloor.showSpinerDialog();

                }
                break;
            case R.id.llWing:
                if (wingDetails == null) {
                    CommonFunctions.getInstance().validationInfoError(getActivity(), LanguageConstants.noWing);
                    return;
                } else {
                    final ArrayList<SpinnerModel> mSpinnerModelswing = new ArrayList<>();
                    SpinnerModel mSpinnerModelwing;
                    for (int count = 0; count < wingDetails.size(); count++) {
                        mSpinnerModelwing = new SpinnerModel();
                        mSpinnerModelwing.setId(String.valueOf(wingDetails.get(count).getWingID()));
                        mSpinnerModelwing.setName(wingDetails.get(count).getWingName());
                        mSpinnerModelswing.add(mSpinnerModelwing);
                    }
                    SpinnerDialog spinnerDialogwing;
                    spinnerDialogwing = new SpinnerDialog(getActivity(), mSpinnerModelswing, LanguageConstants.wing);
                    spinnerDialogwing.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvWing.setText(mSpinnerModelswing.get(position).getName());
                            binding.tvWing.setTag(mSpinnerModelswing.get(position).getId());
                            wingID = mSpinnerModelswing.get(position).getId();
                        }
                    });
                    spinnerDialogwing.showSpinerDialog();

                }
                break;
            case R.id.rvNotification:
                break;
            case R.id.ivQr:
                CommonFunctions.getInstance().newIntent(getActivity(), DecoderActivity.class, Bundle.EMPTY, false, false);
                break;
            case R.id.rlSubmit:
                if (binding.tvCompany.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(getActivity(), LanguageConstants.selectcompanyfirst);
                    return;
                }
                if (binding.tvLocation.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(getActivity(), LanguageConstants.selectlocationfirst);
                    return;
                }
                if (binding.tvBuilding.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(getActivity(), LanguageConstants.selectbuildingfirst);
                    return;
                }
                if (binding.tvFloor.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(getActivity(), LanguageConstants.selectfloorfirst);
                    return;
                }
                if (binding.tvWing.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(getActivity(), LanguageConstants.selectwingfirst);
                    return;
                }
                SessionManager.getInstance().insertIntoPreference(getActivity(), SharedPrefConstants.COMPANYID, companyID);
                SessionManager.getInstance().insertIntoPreference(getActivity(), SharedPrefConstants.LOCATIONID, locationID);
                SessionManager.getInstance().insertIntoPreference(getActivity(), SharedPrefConstants.BUILDINGID, buildingID);
                SessionManager.getInstance().insertIntoPreference(getActivity(), SharedPrefConstants.FLOORID, floorID);
                SessionManager.getInstance().insertIntoPreference(getActivity(), SharedPrefConstants.WINGID, wingID);
                CommonApiCalls.getInstance().getSettingssDetails(getActivity(), companyID, locationID, SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID),
                        SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID), new CommonCallback.Listener() {
                            @Override
                            public void onSuccess(Object object) {
                                SettingsDetailsApiResponse body = (SettingsDetailsApiResponse) object;
                                SettingsDetailsApiResponse.ReturnData data = body.getReturnData();
                                if (data != null && data.getSettingDetails().size() > 0) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("MAXHOURS", Integer.parseInt(data.getSettingDetails().get(0).getMaxHours()));
                                    bundle.putString("STARTTIME", data.getSettingDetails().get(0).getStartTime());
                                    bundle.putString("ENDTIME", data.getSettingDetails().get(0).getEndTime());
                                    CommonFunctions.getInstance().newIntent(getActivity(), CalanderActivityNew.class, bundle, false, false);
                                } else {
                                    CommonFunctions.getInstance().validationInfoError(getActivity(), LanguageConstants.noDataFound);
                                }

                            }

                            @Override
                            public void onFailure(String reason) {

                            }
                        });

                break;
            default:
                break;

        }


    }

    private void getLocationDetailsApi(String email, String password, String UserId, String companyid, String roleid, String locationIDs, String buildingIDs, final String floorIDs, final String wingIDs) {
        CommonApiCalls.getInstance().getLoginDetails(getActivity(), email,
                password, UserId, companyid, roleid, locationIDs, buildingIDs, floorIDs, wingIDs, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        LoginApiResponseModel body = (LoginApiResponseModel) object;
                        LoginApiResponseModel.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            if (body.getReturnData() != null) {
                                locationDetails = data.getLocationDetails();
                                buildingDetails = data.getBuildingDetails();
                                floorDetails = data.getFloorDetails();
                                wingDetails = data.getWingsDetails();
                                if (locationDetails != null && locationDetails.size() > 0) {
                                    binding.tvLocation.setText(locationDetails.get(0).getLocationName());
                                    locationID = String.valueOf(locationDetails.get(0).getLocationID());
                                }
                                if (buildingDetails != null && buildingDetails.size() > 0) {
                                    binding.tvBuilding.setText(buildingDetails.get(0).getBuildingName());
                                    buildingID = String.valueOf(buildingDetails.get(0).getBuildingID());
                                }
                                if (floorDetails != null && floorDetails.size() > 0) {
                                    binding.tvFloor.setText(floorDetails.get(0).getFloorName());
                                    floorID = String.valueOf(floorDetails.get(0).getFloorID());
                                }
                                if (wingDetails != null && wingDetails.size() > 0) {
                                    binding.tvWing.setText(wingDetails.get(0).getWingName());
                                    wingID = String.valueOf(wingDetails.get(0).getWingID());
                                }
                            } else {
                                CommonFunctions.getInstance().successResponseToast(getActivity(), LanguageConstants.dataEmpty);
                            }
                        } else {
                            CommonFunctions.getInstance().successResponseToast(getActivity(), body.getMessage());
                        }


                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }

    private void getbuildDetailsApi(String email, String password, String UserId, String companyid, String roleid, String locationID, String buildingIDs, final String floorIDs, final String wingIDs) {
        CommonApiCalls.getInstance().getLoginDetails(getActivity(), email,
                password, UserId, companyid, roleid, locationID, buildingIDs, floorIDs, wingIDs, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        LoginApiResponseModel body = (LoginApiResponseModel) object;
                        LoginApiResponseModel.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            if (body.getReturnData() != null) {
                                //locationdetails = data.getLocationDetails();
                                buildingDetails = data.getBuildingDetails();
                                floorDetails = data.getFloorDetails();
                                wingDetails = data.getWingsDetails();
                                if (buildingDetails != null && buildingDetails.size() > 0) {
                                    binding.tvBuilding.setText(buildingDetails.get(0).getBuildingName());
                                    buildingID = String.valueOf(buildingDetails.get(0).getBuildingID());
                                }
                                if (floorDetails != null && floorDetails.size() > 0) {
                                    binding.tvFloor.setText(floorDetails.get(0).getFloorName());
                                    floorID = String.valueOf(floorDetails.get(0).getFloorID());
                                }
                                if (wingDetails != null && wingDetails.size() > 0) {
                                    binding.tvWing.setText(wingDetails.get(0).getWingName());
                                    wingID = String.valueOf(wingDetails.get(0).getWingID());
                                }
                            } else {
                                CommonFunctions.getInstance().successResponseToast(getActivity(), LanguageConstants.dataEmpty);
                            }
                        } else {
                            CommonFunctions.getInstance().successResponseToast(getActivity(), body.getMessage());
                        }


                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }

    private void getFloorDetailsApi(String email, String password, String userid, String companyid, String roleid, String locationID, final String buildingID, final String floorIDs, final String wingIDs) {
        CommonApiCalls.getInstance().getLoginDetails(getActivity(), email,
                password, userid, companyid, roleid, locationID, buildingID, floorIDs, wingIDs, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        LoginApiResponseModel body = (LoginApiResponseModel) object;
                        LoginApiResponseModel.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            if (body.getReturnData() != null) {
                                floorDetails = data.getFloorDetails();
                                wingDetails = data.getWingsDetails();
                                if (floorDetails != null && floorDetails.size() > 0) {
                                    binding.tvFloor.setText(floorDetails.get(0).getFloorName());
                                    floorID = String.valueOf(floorDetails.get(0).getFloorID());
                                }
                                if (wingDetails != null && wingDetails.size() > 0) {
                                    binding.tvWing.setText(wingDetails.get(0).getWingName());
                                    wingID = String.valueOf(wingDetails.get(0).getWingID());
                                }
                            } else {
                                CommonFunctions.getInstance().successResponseToast(getActivity(), LanguageConstants.dataEmpty);
                            }
                        } else {
                            CommonFunctions.getInstance().successResponseToast(getActivity(), body.getMessage());
                        }


                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }

    private void getWingDetailsApi(String email, String password, String Userid, String companyid, String roleid, String locationID, final String buildingID, String floorID, final String wingIDs) {
        CommonApiCalls.getInstance().getLoginDetails(getActivity(), email,
                password, Userid, companyid, roleid, locationID, buildingID, floorID, wingIDs, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        LoginApiResponseModel body = (LoginApiResponseModel) object;
                        LoginApiResponseModel.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            if (body.getReturnData() != null) {
                                //locationdetails = data.getLocationDetails();
                                //buildingDetails = data.getBuildingDetails();
                                wingDetails = data.getWingsDetails();
                                if (wingDetails != null && wingDetails.size() > 0) {
                                    binding.tvWing.setText(wingDetails.get(0).getWingName());
                                }
                            } else {
                                CommonFunctions.getInstance().successResponseToast(getActivity(), LanguageConstants.dataEmpty);
                            }
                        } else {
                            CommonFunctions.getInstance().successResponseToast(getActivity(), body.getMessage());
                        }


                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
