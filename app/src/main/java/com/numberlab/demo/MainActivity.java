package com.numberlab.demo;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends Activity {

    private final Random random = new Random();
    private TextView numberView, historyView;
    private int count = 0;
    private String history = "";

    private int dp(float value) {
        return (int)(value * getResources()
            .getDisplayMetrics().density);
    }

    private GradientDrawable shape(int color, int stroke) {
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(color);
        bg.setCornerRadius(dp(18));
        bg.setStroke(dp(1), stroke);
        return bg;
    }

    private TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(color);
        t.setTextSize(size);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(10, 5, 20));
        getWindow().setNavigationBarColor(Color.rgb(10, 5, 20));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(25), dp(18), dp(20));
        root.setBackgroundColor(Color.rgb(8, 5, 15));

        TextView title = text(
            "NUMBER LAB", 28, Color.rgb(204, 145, 255)
        );
        root.addView(title);

        TextView subtitle = text(
            "RANDOM NUMBER SIMULATOR",
            12, Color.rgb(150, 120, 185)
        );
        root.addView(subtitle);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER);
        card.setPadding(dp(16), dp(22), dp(16), dp(22));
        card.setBackground(
            shape(Color.rgb(22, 13, 35), Color.rgb(125, 50, 205))
        );

        LinearLayout.LayoutParams cp =
            new LinearLayout.LayoutParams(-1, dp(220));
        cp.topMargin = dp(28);
        root.addView(card, cp);

        TextView label = text(
            "RANDOM DIGIT", 14, Color.rgb(190, 140, 255)
        );
        card.addView(label);

        numberView = text("--", 88, Color.WHITE);
        numberView.setShadowLayer(
            dp(16), 0, 0, Color.rgb(160, 50, 255)
        );
        card.addView(numberView);

        Button generate = new Button(this);
        generate.setText("GENERATE NUMBER");
        generate.setTextColor(Color.WHITE);
        generate.setBackground(
            shape(Color.rgb(100, 30, 175), Color.rgb(195, 115, 255))
        );

        LinearLayout.LayoutParams bp =
            new LinearLayout.LayoutParams(-1, dp(58));
        bp.topMargin = dp(22);
        root.addView(generate, bp);

        TextView historyTitle = text(
            "RECENT HISTORY", 16, Color.rgb(190, 140, 255)
        );

        LinearLayout.LayoutParams hp =
            new LinearLayout.LayoutParams(-1, dp(55));
        hp.topMargin = dp(20);
        root.addView(historyTitle, hp);

        historyView = text(
            "No numbers generated yet", 14, Color.WHITE
        );
        historyView.setGravity(Gravity.CENTER_VERTICAL);
        historyView.setPadding(dp(16), dp(14), dp(16), dp(14));
        historyView.setBackground(
            shape(Color.rgb(19, 13, 29), Color.rgb(75, 40, 110))
        );

        root.addView(
            historyView,
            new LinearLayout.LayoutParams(-1, -2)
        );

        TextView footer = text(
            "EDUCATIONAL SIMULATION",
            11, Color.rgb(110, 90, 140)
        );

        LinearLayout.LayoutParams fp =
            new LinearLayout.LayoutParams(-1, dp(65));
        root.addView(footer, fp);

        generate.setOnClickListener(v -> generateNumber());

        setContentView(root);
    }

    private void generateNumber() {
        int value = random.nextInt(10);
        count++;

        numberView.setText(String.valueOf(value));

        history = count + ".    " + value + "\n" + history;

        String[] lines = history.split("\n");
        StringBuilder limited = new StringBuilder();

        for (int i = 0; i < Math.min(lines.length, 10); i++) {
            limited.append(lines[i]).append("\n");
        }

        history = limited.toString();
        historyView.setText(history);
    }
}
