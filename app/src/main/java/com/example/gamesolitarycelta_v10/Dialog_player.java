package com.example.gamesolitarycelta_v10;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;


public class Dialog_player {
    public Dialog_player(Context context){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_player);

        Button btn_ok = dialog.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }

}
