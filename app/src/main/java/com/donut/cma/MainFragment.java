package com.donut.cma;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.donut.cma.CropPriceAdapter;
import com.donut.cma.CropPrices;
import com.donut.cma.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private String myUrl="https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd0000012bd8e5feba7743e777e2875dfe41b11b&format=csv&offset=0";
    public List<CropPrices> CropPriceList;
    public RecyclerView recyclerView;
    public CropPriceAdapter mAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_fragment, container,false);
        CropPriceList= new ArrayList<>();
        CropPrices c1=new CropPrices("0","1","2","3","4","5","6","7","8","9");

        CropPriceList.add(c1);

        Button button1 = (Button) rootView.findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if(isConnected){
                    Toast.makeText(getActivity(),"Internet Connectivity",Toast.LENGTH_LONG).show();
                    new Thread(new Runnable() {
                        public void run() {
                            DownloadFiles();
                        }
                    }).start();
                    Toast.makeText(getActivity(),"Prices Downloaded",Toast.LENGTH_LONG).show();
                    getData();
                }
                else {
                    Toast.makeText(getActivity(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
                }
            }
        });
        //DownloadFiles();
        getData();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.CropPriceRecycler);

        mAdapter = new CropPriceAdapter(CropPriceList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        getData();

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

    }

    public void getData(){
        File temp= new File(getActivity().getFilesDir(), "marketPricesData.csv");
        //CropPriceList= new ArrayList<>();
        //CropPrices c1=new CropPrices("0","1","2","3","4","5","6","7","8","9");

        //CropPriceList.add(c1);

        if(temp.length()==0){
            Toast.makeText(getActivity(),"Download Prices First",Toast.LENGTH_LONG).show();
        }
        else {
            try {
                CropPriceList=new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(temp));

                String line=br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] a=line.split(",");
                    CropPrices c=new CropPrices(a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9]);

                    CropPriceList.add(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //mAdapter.notifyDataSetChanged();
    }


    public void DownloadFiles(){

        try {
            URL u = new URL(myUrl);
            InputStream is = u.openStream();

            DataInputStream dis = new DataInputStream(is);

            byte[] buffer = new byte[1024];
            int length;
            File file=new File(getActivity().getFilesDir(), "marketPricesData.csv");
            FileOutputStream fos = new FileOutputStream(file);
            while ((length = dis.read(buffer))>0) {
                fos.write(buffer, 0, length);
                Log.i("Wrote to file", String.valueOf(length));
            }
            Log.e("Wrote to file", String.valueOf(file.length()));
            //Toast.makeText(getActivity(),file.getName()+" "+file.length(),Toast.LENGTH_LONG).show();
        } catch (MalformedURLException mue) {
            Log.e("SYNC getUpdate", "malformed url error", mue);
        } catch (IOException ioe) {
            Log.e("SYNC getUpdate", "io error", ioe);
        } catch (SecurityException se) {
            Log.e("SYNC getUpdate", "security error", se);
        }

    }

}
