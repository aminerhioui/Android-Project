package com.example.myapplication.ui.diagnosis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDiagnosisBinding;
import com.example.myapplication.databinding.FragmentDiagnosisBinding;
import com.example.myapplication.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DiagnosisFragment extends Fragment {
    TextView result, confidence;
    ImageView imageView;
    Button picture,picture2;
    int imageSize = 224;
    private FragmentDiagnosisBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diagnosis,container,false);
        result = root.findViewById(R.id.result);
        confidence = root.findViewById(R.id.confidence);
        imageView = root.findViewById(R.id.imageView);
        picture = root.findViewById(R.id.button);
        picture2= root.findViewById(R.id.button2);
        picture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
        picture2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(cameraIntent, 2);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                }


            }
        });
        return root;
    }
    public void classifyImage (Bitmap image)
    {
        try {
            Model model = Model.newInstance(getActivity().getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getWidth());
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }
            inputFeature0.loadBuffer(byteBuffer);
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Acne", "Acne cystic", "Acne dosed comedo", "Actinic", "AIDS", "allergic contact dermatitis", "alopecia", "angiokeratomas", "Arsenical keratosis", "atypical nevi", "basal cell carcinoma", "basal cell nevus syndrome", "benign familial chronic pemphigus", "Bowens disease", "bullous pemphigoid", "cellulitis", "chilblains perniosis", "chondrodermatitis nodul,aris", "crest syndrome", "Cutaneous horn", "Cutaneous T-cell lymphoma", "dariers disease", "dermatitis herpetiformis", "dermatofibroma", "dermatomyositis", "Diabetic bullae", "distal subungual onychomycosis", "Drug eruption", "dyshidrosis", "Eczema", "epidermolysis bullosa", "erythema infectiosum", "erythroplasia queyrat", "Folliculitis", "fordyce spots", "furuncles carbuncles", "genital warts", "granulation tissue", "grovers disease", "hemangioma", "herpes", "Hidradenitis suppurativa", "hives", "Hyperhidrosis", "Impetigo", "keratolysis exfoliativa", "lentigo", "leukoplakia", "lichen simplex chronicus", "lupus", "malignant melanoma", "melanocytic nevi", "metastasis", "Milia", "Minocycline", "molluscum contagiosum", "morphea", "mucous cyst", "neurotic excoriation", "nevus", "onycholysis", "Paget disease", "patch testing", "Pemphigus", "perioral dermatitis", "phototoxic reactions", "pityriasis lichenoides", "polymorphous", "pompholyx", "porphyrias", "prurigo nodularis", "pseudomonas cellulitis", "pseudomonas folliculitis", "psoriasis", "pyogenic granuloma", "Rhinophyma", "rhus dermatitis", "Rosacea", "scabies", "scarlet fever", "scleroderma", "sebaceous glands", "sebaceous hyperplasia", "seborrheic dermatitis", "seborrheic keratosis", "skin tags polyps", "squamous cell carcinoma", "staphylococcal folliculitis", "stasis dermatitis", "stucco keratosis", "sun damaged skin", "sycosis barbae", "syphilis", "syringoma", "telangiectasias", "tick bite", "Tinea", "tuberous sclerosis", "varicella", "vasculitis", "venous lake", "venous malformations", "viral exanthems", "vitiligo", "warts", "xanthomas"};
            result.setText(classes[maxPos]);
            confidence.setText(String.valueOf(maxConfidence));


            // Releases model resources if no longer used.
            model.close();
        }
        catch (IOException e){

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            if(requestCode == 1 ){
                Bitmap image = (Bitmap) data.getExtras().get("data") ;
                int dimension = Math.min(image.getWidth(),image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension ,dimension) ;
                imageView.setImageBitmap(image) ;
                image = Bitmap.createScaledBitmap(image , imageSize, imageSize, false) ;
                classifyImage(image) ;
            }
            else if(requestCode == 2 && data !=null)    {

                Uri image =data.getData() ;
                try {
                    Bitmap image1 =MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),image) ;
                    int dimension = Math.min(image1.getWidth(),image1.getHeight());
                    image1 = ThumbnailUtils.extractThumbnail(image1, dimension ,dimension) ;
                    imageView.setImageBitmap(image1) ;
                    image1 = Bitmap.createScaledBitmap(image1 , imageSize, imageSize, false) ;
                    classifyImage(image1) ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}