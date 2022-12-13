package com.example.girlsquotesactivty;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.girlsquotesactivty.databinding.ActivityQuotesWithBeckgroundBinding;


public class QuotesWithBeckground extends AppCompatActivity {
    ActivityQuotesWithBeckgroundBinding binding;
    String category, quotes;
    int galleryCode = 100;
    int i = 0;
    int QuotesImage[] = {R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10};
    int colorfile[] = {R.color.black,R.color.purple_200,R.color.purple_500};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_with_beckground);
        binding = ActivityQuotesWithBeckgroundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listing();
        onClickButton();
    }

    private void onClickButton() {
        binding.imageView.setOnClickListener(view -> {
            binding.imageView.setImageResource(QuotesImage[i]);
            i++;
            if (i == 9) {
                i = 0;
            }
        });
        binding.imgBack.setOnClickListener(view -> {
//            Intent i = new Intent(QuotesWithBeckground.this, QuotesActivity.class);
//            startActivity(i);
            finish();
        });
        binding.imgCopy.setOnClickListener(v -> {

            String copyQuote = quotes;
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Text Copy", copyQuote);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
        });
        binding.imgLikeEmpty.setOnClickListener(v -> {
            binding.imgLikeEmpty.setVisibility(View.GONE);
            binding.imgLikeFill.setVisibility(View.VISIBLE);
            Toast.makeText(QuotesWithBeckground.this, "Added to My Favourite", Toast.LENGTH_SHORT).show();
        });
        binding.imgLikeFill.setOnClickListener(v -> {
            binding.imgLikeEmpty.setVisibility(View.VISIBLE);
            binding.imgLikeFill.setVisibility(View.GONE);
        });
        binding.imgShare.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.share_item);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView txtText = dialog.findViewById(R.id.txtText);
            TextView txtImage = dialog.findViewById(R.id.txtImage);

            txtText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = quotes;
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
            });
            dialog.show();
        });
        binding.imgPencil.setOnClickListener(view -> {
            Dialog dialog = new Dialog(QuotesWithBeckground.this);
            dialog.setContentView(R.layout.beckground_item);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView txtGallery = dialog.findViewById(R.id.txtGallery);
            TextView txtBackgrounds = dialog.findViewById(R.id.txtBackgrounds);

            txtGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), galleryCode);
                    Log.e("TAG", "onClick    : "+galleryIntent+galleryCode);
                    dialog.dismiss();
                }
            });
            dialog.show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == galleryCode) {
            if (resultCode == -1 && data != null) {
                Uri uri = data.getData();
                binding.imageView.setImageURI(uri);
            }
        } else {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void listing() {
        if (getIntent() != null) {
            category = getIntent().getStringExtra("category");
            quotes = getIntent().getStringExtra("quotes");
        }
        binding.txtHeading2.setText(category);
        binding.txtQuotes.setText(quotes);
    }
}