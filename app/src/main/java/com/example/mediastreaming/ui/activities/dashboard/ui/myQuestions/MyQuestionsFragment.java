package com.example.mediastreaming.ui.activities.dashboard.ui.myQuestions;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediastreaming.R;
import com.example.mediastreaming.base.BaseFragment;
import com.example.mediastreaming.databinding.DialogEdittextBinding;
import com.example.mediastreaming.databinding.FragmentMyQuestionsBinding;
import com.example.mediastreaming.databinding.RecyclerItemQuestionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyQuestionsFragment extends BaseFragment {
    private static final int REQUEST_GALLERY_DOCUMENTS = 10;
    private static final String TAG = "MyQuestionsFragment";
    private FragmentMyQuestionsBinding binding;
    private MyQuestionFragViewModel model;
    private Dialog dialog;
    private String latex;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_questions, container, false);
        model = new ViewModelProvider(this).get(MyQuestionFragViewModel.class);
        binding.setLifecycleOwner(requireActivity());
        binding.setModel(model);
        init();
        observe();
        return binding.getRoot();
    }

    private void init(){
//        binding.webView.getSettings().setJavaScriptEnabled(true);
        /*String latex = "$\\omega=\\frac{\\theta_{2}-\\theta_{1}}{t_{2}-t_{1}}, \\quad \\alpha=\\frac{\\omega_{2}-\\omega_{1}}{t_{2}-t_{1}}, \\quad \\Gamma=F . r$ and $I=m r^{2}$.<br>" +
                "<br>" +
                "The symbols have standard meanings.<br>" +
                "<br>" +
                "3. Find the dimensions of<br>" +
                "(a) electric field $E$,<br>" +
                "(b) magnetic field $B$ and<br>" +
                "(c) magnetic permeability $\\mu_{0}$<br>" +
                "The relevant equations are<br>" +
                "$F=q E, F=q v B$, and $B=\\frac{\\mu_{0} I}{2 \\pi a} ;$<br>" +
                "<br>" +
                "where $F$ is force, $q$ is charge, $v$ is speed, $I$ is current, and $a$ is distance.<br>" +
                "<br>" +
                "4. Find the dimensions of<br>" +
                "<br>" +
                "(a) electric dipole moment $p$ and<br>" +
                "<br>" +
                "(b) magnetic dipole moment $M$.<br>" +
                "<br>" +
                "The defining equations are $p=q . d$ and $M=I A$;<br>" +
                "<br>" +
                "where $d$ is distance, $A$ is area, $q$ is charge and $I$ is current.<br>" +
                "<br>" +
                "5. Find the dimensions of Planck's constant $h$ from the equation $E=h v$ where $E$ is the energy and $v$ is the frequency.<br>" +
                "<br>" +
                "6. Find the dimensions of<br>" +
                "<br>" +
                "(a) the specific heat capacity $c$,<br>" +
                "<br>" +
                "(b) the coefficient of linear expansion $\\alpha$ and<br>" +
                "<br>" +
                "(c) the gas constant $R$.<br>" +
                "<br>" +
                "Some of the equations involving these quantities are $Q=m c\\left(T_{2}-T_{1}\\right), l_{t}=l_{0}\\left[1+\\alpha\\left(T_{2}-T_{1}\\right)\\right]$ and $P V=n R T$.<br>" +
                "<br>" +
                "7. Taking force, length and time to be the fundamental quantities find the dimensions of<br>" +
                "(a) density,<br>" +
                "(b) pressure,<br>" +
                "(c) momentum and<br>" +
                "(d) energy.<br>" +
                "<br>" +
                "8. Suppose the acceleration due to gravity at a place is $10 \\mathrm{~m} / \\mathrm{s}^{2}$. Find its value in $\\mathrm{cm} /(\\text { minute })^{2}$.<br>" +
                "<br>" +
                "9. The average speed of a snail is $0.020 \\mathrm{miles} / \\mathrm{hour}$ and that of a leopard is 70 miles/hour. Convert these speeds in SI units.<br>" +
                "<br>" +
                "10. The height of mercury column in a barometer in a Calcutta laboratory was recorded to be $75 \\mathrm{~cm}$. Calculate this pressure in SI and CGS units using the following data : Specific gravity of mercury $=13 \\cdot 6$, Density of water $=10^{3} \\mathrm{~kg} / \\mathrm{m}^{3}, g=9.8 \\mathrm{~m} / \\mathrm{s}^{2}$ at Calcutta. Pressure $=h \\rho g$ in usual symbols.<br>" +
                "<br>" +
                "11. Express the power of a 100 watt bulb in CGS unit.<br>" +
                "12. The normal duration of I.Sc. Physics practical period in Indian colleges is 100 minutes. Express this period in microcenturies. 1 microcentury $=10^{-6} \\times 100$ years. How many microcenturies did you sleep yesterday?<br>" +
                "<br>" +
                "13. The surface tension of water is $72 \\mathrm{dyne} / \\mathrm{cm}$. Convert it in SI unit.<br>" +
                "<br>" +
                "14. The kinetic energy $K$ of a rotating body depends on its moment of inertia $I$ and its angular speed $\\omega$. Assuming the relation to be $K=k I^{a} \\omega^{b}$ where $k$ is a dimensionless constant, find $a$ and $b$. Moment of inertia of a sphere about its diameter is $\\frac{2}{5} M r^{2}$.<br>" +
                "<br>" +
                "15. Theory of relativity reveals that mass can be converted into energy. The energy $E$ so obtained is proportional to certain powers of mass $m$ and the speed $c$ of light. Guess a relation among the quantities using the method of dimensions.<br>" +
                "<br>" +
                "16. Let $I=$ current through a conductor, $R=$ its resistance and $V=$ potential difference across its ends. According to Ohm's law, product of two of these quantities equals the third. Obtain Ohm's law from dimensional analysis. Dimensional formulae for $R$ and $V$ are $\\mathrm{ML}^{2} \\mathrm{I}^{-2} \\mathrm{~T}^{-3}$ and $\\mathrm{ML}^{2} \\mathrm{~T}^{-3} \\mathrm{I}^{-1}$ respectively.<br>" +
                "<br>" +
                "17. The frequency of vibration of a string depends on the length $L$ between the nodes, the tension $F$ in the string and its mass per unit length $m$. Guess the expression for its frequency from dimensional analysis.<br>" +
                "<br>" +
                "18. Test if the following equations are dimensionally correct :<br>" +
                "(a) $h=\\frac{2 S \\cos \\theta}{\\rho r g}$<br>" +
                "(b) $v=\\sqrt{ } \\frac{P}{\\rho}$,<br>" +
                "(c) $V=\\frac{\\pi P r^{4} t}{8 \\eta l}$,<br>" +
                "(d) $v=\\frac{1}{2 \\pi} \\sqrt{\\frac{m g l}{I}}$;<br>" +
                "<br>" +
                "where $h=$ height, $S$ = surface tension, $\\rho=$ density, $P=$ pressure, $V=$ volume, $\\eta=$ coefficient of viscosity, $v=$ frequency and $I=$ moment of inertia.<br>" +
                "<br>" +
                "19. Let $x$ and $a$ stand for distance. Is $\\int \\frac{d x}{\\sqrt{a^{2}-x^{2}}}$ $=\\frac{1}{a} \\sin ^{-1} \\frac{a}{x}$ dimensionally correct?<br>" +
                "<br>" +
                "\\title{ANSWERS}" +
                "<br>" +
                "OBJECTIVE I<br>" +
                "1. (b)<br>" +
                "2. (d)<br>" +
                "3. (d)<br>" +
                "4. (c)<br>" +
                "5. (a)<br>" +
                "6. (a)<br>" +
                "<br>" +
                "\\section*{OBJECTIVE II}<br>" +
                "1. (c), (d)<br>" +
                "2. (a), (b), (d)<br>" +
                "3. (a), (b), (c)<br>" +
                "<br>" +
                "\\section*{EXERCISES}<br>" +
                "1. (a) $\\mathrm{MLT}^{-1}$<br>" +
                "(b) $\\mathrm{T}^{-1}$<br>" +
                "(c) $\\mathrm{ML}^{-1} \\mathrm{~T}^{-2}$<br>" +
                "<br>" +
                "2. (a) $\\mathrm{T}^{-1}$<br>" +
                "<br>" +
                "(b) $\\mathrm{T}^{-2}$<br>" +
                "<br>" +
                "(c) $\\mathrm{ML}^{2} \\mathrm{~T}^{-2}$<br>" +
                "<br>" +
                "(d) $\\mathrm{ML}^{2}$<br>" +
                "<br>" +
                "3. (a) $\\mathrm{MLT}^{-3} \\mathrm{I}^{-1}$<br>" +
                "<br>" +
                "(b) $\\mathrm{MT}^{-2} \\mathrm{I}^{-1}$<br>" +
                "<br>" +
                "(c) $\\mathrm{MLT}^{-2} \\mathrm{I}^{-2}$<br>" +
                "<br>" +
                "4. (a) LTI<br>" +
                "<br>" +
                "(b) $\\mathrm{L}^{2} \\mathrm{I}$<br>" +
                "<br>" +
                "5. $\\mathrm{ML}^{2} \\mathrm{~T}^{-1}$<br>" +
                "6. (a) $\\mathrm{L}^{2} \\mathrm{~T}^{-2} \\mathrm{~K}^{-1}$<br>" +
                "(b) $\\mathrm{K}^{-1}$<br>" +
                "(c) $\\mathrm{ML}^{2} \\mathrm{~T}^{-2} \\mathrm{~K}^{-1}(\\mathrm{~mol})^{-1}$";


        String content = "<!DOCTYPE html><html><head>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "<script type='text/x-mathjax-config'>"
                + "MathJax.Hub.Config({"
                + "  tex2jax: {inlineMath: [['$','$'], ['\\\\(','\\\\)']]},"
                + "  TeX: { equationNumbers: { autoNumber: 'AMS' } }"
                + "});"
                + "</script>"
                + "<script src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?"
                + "config=TeX-MML-AM_CHTML' async></script>"
                + "</head><body>"
                + latex
                + "</body></html>";*/

        latex = "Which of the following sets cannot enter into the list of fundamental quantities in any system of units?<br>" +
                "<br>" +
                "(a) length, mass and velocity,<br>" +
                "<br>" +
                "(b) length, time and velocity,<br>" +
                "<br>" +
                "(c) mass, time and velocity,<br>" +
                "<br>" +
                "(d) length, time and mass.<br>" +
                "<br>" +
                "2. A physical quantity is measured and the result is expressed as $n u$ where $u$ is the unit used and $n$ is the numerical value. If the result is expressed in various units then<br>" +
                "(a) $n \\propto \\operatorname{size}$ of $u$<br>" +
                "(b) $n \\propto u^{2}$<br>" +
                "(c) $n \\propto \\sqrt{ } u$<br>" +
                "(d) $n \\propto \\frac{1}{u}$.<br>" +
                "<br>" +
                "3. Suppose a quantity $x$ can be dimensionally represented in terms of $\\mathrm{M}, \\mathrm{L}$ and $\\mathrm{T}$, that is, $[x]=\\mathrm{M}^{a} \\mathrm{~L}^{b} \\mathrm{~T}^{c}$. The quantity mass<br>" +
                "<br>" +
                "(a) can always be dimensionally represented in terms of L, T and $x$,<br>" +
                "<br>" +
                "(b) can never be dimensoinally represented in terms of<br>" +
                "L, $\\mathrm{T}$ and $x$,<br>" +
                "<br>" +
                "(c) may be represented in terms of $\\mathrm{L}, \\mathrm{T}$ and $x$ if $a=0$,<br>" +
                "<br>" +
                "(d) may be represented in terms of $\\mathrm{L}, \\mathrm{T}$ and $x$ if $a \\neq 0$.<br>" +
                "<br>" +
                "4. A dimensionless quantity<br>" +
                "(a) never has a unit,<br>" +
                "(b) always has a unit,<br>" +
                "(c) may have a unit,<br>" +
                "(d) does not exist.<br>" +
                "<br>" +
                "5. A unitless quantity<br>" +
                "<br>" +
                "(a) never has a nonzero dimension,<br>" +
                "<br>" +
                "(b) always has a nonzero dimension,<br>" +
                "<br>" +
                "(c) may have a nonzero dimension,<br>" +
                "<br>" +
                "(d) does not exist.<br>" +
                "<br>" +
                "6. $\\int \\frac{d x}{\\sqrt{2 a x-x^{2}}}=a^{n} \\sin ^{-1} \\frac{x}{a}-1$.<br>" +
                "<br>" +
                "The value of $n$ is<br>" +
                "(a) 0<br>" +
                "(b) -1<br>" +
                "(c) 1<br>" +
                "(d) none of these.<br>" +
                "<br>" +
                "You may use dimensional analysis to solve the problem.<br>" +
                "<br>" +
                "\\section*{OBJECTIVE II}<br>" +
                "<br>" +
                "1. The dimensions $\\mathrm{ML}^{-1} \\mathrm{~T}^{-2}$ may correspond to<br>" +
                "<br>" +
                "(a) work done by a force<br>" +
                "<br>" +
                "(b) linear momentum<br>" +
                "<br>" +
                "(c) pressure<br>" +
                "<br>" +
                "(d) energy per unit volume.<br>" +
                "<br>" +
                "2. Choose the correct statement(s) :<br>" +
                "<br>" +
                "(a) A dimensionally correct equation may be correct.<br>" +
                "<br>" +
                "(b) A dimensionally correct equation may be incorrect.<br>" +
                "<br>" +
                "(c) A dimensionally incorrect equation may be correct.<br>" +
                "<br>" +
                "(d) A dimensionally incorrect equation may be incorrect.<br>" +
                "3. Choose the correct statement(s) :<br>" +
                "<br>" +
                "(a) All quantities may be represented dimensionally in terms of the base quantities.<br>" +
                "<br>" +
                "(b) A base quantity cannot be represented dimensionally in terms of the rest of the base quantities.<br>" +
                "<br>" +
                "(c) The dimension of a base quantity in other base quantities is always zero.<br>" +
                "<br>" +
                "(d) The dimension of a derived quantity is never zero in any base quantity.<br>" +
                "<br>" +
                "\\section*{EXERCISES}<br>" +
                "<br>" +
                "1. Find the dimensions of<br>" +
                "<br>" +
                "(a) linear momentum,<br>" +
                "<br>" +
                "(b) frequency and<br>" +
                "<br>" +
                "(c) pressure.<br>" +
                "2. Find the dimensions of<br>" +
                "(a) angular speed $\\omega$,<br>" +
                "(b) angular acceleration $\\alpha$,<br>" +
                "(c) torque $\\Gamma$ and<br>" +
                "(d) moment of interia $I$.<br>" +
                "<br>" +
                "Some of the equations involving these quantities are<br>" +
                "$\\omega=\\frac{\\theta_{2}-\\theta_{1}}{t_{2}-t_{1}}, \\quad \\alpha=\\frac{\\omega_{2}-\\omega_{1}}{t_{2}-t_{1}}, \\quad \\Gamma=F . r$ and $I=m r^{2}$.<br>" +
                "<br>" +
                "The symbols have standard meanings.<br>" +
                "<br>" +
                "3. Find the dimensions of<br>" +
                "(a) electric field $E$,<br>" +
                "(b) magnetic field $B$ and<br>" +
                "(c) magnetic permeability $\\mu_{0}$<br>" +
                "The relevant equations are<br>" +
                "$F=q E, F=q v B$, and $B=\\frac{\\mu_{0} I}{2 \\pi a} ;$<br>" +
                "<br>" +
                "where $F$ is force, $q$ is charge, $v$ is speed, $I$ is current, and $a$ is distance.<br>" +
                "<br>" +
                "4. Find the dimensions of<br>" +
                "<br>" +
                "(a) electric dipole moment $p$ and<br>" +
                "<br>" +
                "(b) magnetic dipole moment $M$.<br>" +
                "<br>" +
                "The defining equations are $p=q . d$ and $M=I A$;<br>" +
                "<br>" +
                "where $d$ is distance, $A$ is area, $q$ is charge and $I$ is current.<br>" +
                "<br>" +
                "5. Find the dimensions of Planck's constant $h$ from the equation $E=h v$ where $E$ is the energy and $v$ is the frequency.<br>" +
                "<br>" +
                "6. Find the dimensions of<br>" +
                "<br>" +
                "(a) the specific heat capacity $c$,<br>" +
                "<br>" +
                "(b) the coefficient of linear expansion $\\alpha$ and<br>" +
                "<br>" +
                "(c) the gas constant $R$.<br>" +
                "<br>" +
                "Some of the equations involving these quantities are $Q=m c\\left(T_{2}-T_{1}\\right), l_{t}=l_{0}\\left[1+\\alpha\\left(T_{2}-T_{1}\\right)\\right]$ and $P V=n R T$.<br>" +
                "<br>" +
                "7. Taking force, length and time to be the fundamental quantities find the dimensions of<br>" +
                "(a) density,<br>" +
                "(b) pressure,<br>" +
                "(c) momentum and<br>" +
                "(d) energy.<br>" +
                "<br>" +
                "8. Suppose the acceleration due to gravity at a place is $10 \\mathrm{~m} / \\mathrm{s}^{2}$. Find its value in $\\mathrm{cm} /(\\text { minute })^{2}$.<br>" +
                "<br>" +
                "9. The average speed of a snail is $0.020 \\mathrm{miles} / \\mathrm{hour}$ and that of a leopard is 70 miles/hour. Convert these speeds in SI units.<br>" +
                "<br>" +
                "10. The height of mercury column in a barometer in a Calcutta laboratory was recorded to be $75 \\mathrm{~cm}$. Calculate this pressure in SI and CGS units using the following data : Specific gravity of mercury $=13 \\cdot 6$, Density of water $=10^{3} \\mathrm{~kg} / \\mathrm{m}^{3}, g=9.8 \\mathrm{~m} / \\mathrm{s}^{2}$ at Calcutta. Pressure $=h \\rho g$ in usual symbols.<br>" +
                "<br>" +
                "11. Express the power of a 100 watt bulb in CGS unit.<br>" +
                "12. The normal duration of I.Sc. Physics practical period in Indian colleges is 100 minutes. Express this period in microcenturies. 1 microcentury $=10^{-6} \\times 100$ years. How many microcenturies did you sleep yesterday?<br>" +
                "<br>" +
                "13. The surface tension of water is $72 \\mathrm{dyne} / \\mathrm{cm}$. Convert it in SI unit.<br>" +
                "<br>" +
                "14. The kinetic energy $K$ of a rotating body depends on its moment of inertia $I$ and its angular speed $\\omega$. Assuming the relation to be $K=k I^{a} \\omega^{b}$ where $k$ is a dimensionless constant, find $a$ and $b$. Moment of inertia of a sphere about its diameter is $\\frac{2}{5} M r^{2}$.<br>" +
                "<br>" +
                "15. Theory of relativity reveals that mass can be converted into energy. The energy $E$ so obtained is proportional to certain powers of mass $m$ and the speed $c$ of light. Guess a relation among the quantities using the method of dimensions.<br>" +
                "<br>" +
                "16. Let $I=$ current through a conductor, $R=$ its resistance and $V=$ potential difference across its ends. According to Ohm's law, product of two of these quantities equals the third. Obtain Ohm's law from dimensional analysis. Dimensional formulae for $R$ and $V$ are $\\mathrm{ML}^{2} \\mathrm{I}^{-2} \\mathrm{~T}^{-3}$ and $\\mathrm{ML}^{2} \\mathrm{~T}^{-3} \\mathrm{I}^{-1}$ respectively.<br>" +
                "<br>" +
                "17. The frequency of vibration of a string depends on the length $L$ between the nodes, the tension $F$ in the string and its mass per unit length $m$. Guess the expression for its frequency from dimensional analysis.<br>" +
                "<br>" +
                "18. Test if the following equations are dimensionally correct :<br>" +
                "(a) $h=\\frac{2 S \\cos \\theta}{\\rho r g}$<br>" +
                "(b) $v=\\sqrt{ } \\frac{P}{\\rho}$,<br>" +
                "(c) $V=\\frac{\\pi P r^{4} t}{8 \\eta l}$,<br>" +
                "(d) $v=\\frac{1}{2 \\pi} \\sqrt{\\frac{m g l}{I}}$;<br>" +
                "<br>" +
                "where $h=$ height, $S$ = surface tension, $\\rho=$ density, $P=$ pressure, $V=$ volume, $\\eta=$ coefficient of viscosity, $v=$ frequency and $I=$ moment of inertia.<br>" +
                "<br>" +
                "19. Let $x$ and $a$ stand for distance. Is $\\int \\frac{d x}{\\sqrt{a^{2}-x^{2}}}$ $=\\frac{1}{a} \\sin ^{-1} \\frac{a}{x}$ dimensionally correct?<br>" +
                "<br>";

        //binding.webView.loadDataWithBaseURL("file:///android_asset/", convertLatexToHtml(latex), "text/html", "UTF-8", "");
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Adapter adapter = new Adapter(getQuestions());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
    }

    private List<Question> getQuestions(){
//        String questions = Utils.readRawTextFile(requireContext(), R.raw.questions);
        String questions = latex;
        assert questions != null;
        String[] list = questions.split("\\d+\\.\\s");
        List<Question> q_list = new ArrayList<>();
        for (String s:list){
            Question q = new Question(s,-1,false);
            q_list.add(q);
        }
        return q_list;
    }

    public String convertLatexToHtml(String latex) {
        StringBuilder html = new StringBuilder();
        String[] lines = latex.split("<br>");
        for (String line : lines) {
            if (line.startsWith("\\title{")) {
                html.append("<h1>").append(line.substring(7, line.length() - 1)).append("</h1><br>");
            } else if (line.startsWith("\\section*{") || line.startsWith("\\section{")) {
                html.append("<h2>").append(line.substring(line.indexOf("{") + 1, line.length() - 1)).append("</h2><br>");
            } else if (line.startsWith("\\subsection*{") || line.startsWith("\\subsection{")) {
                html.append("<h3>").append(line.substring(line.indexOf("{") + 1, line.length() - 1)).append("</h3><br>");
            } else {
                // For other lines, we simply append them as they are.
                // You can add more parsing logic here for other LaTeX commands.
                html.append(line).append("<br>");
            }
        }

        // Wrap the content with MathJax configuration and HTML tags
        String htmlWithMathJax = "<!DOCTYPE html><html><head>"
                + "<meta charset='utf-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "<script type='text/x-mathjax-config'>"
                + "MathJax.Hub.Config({"
                + "  tex2jax: {inlineMath: [['$','$'], ['\\\\(','\\\\)']]},"
                + "  TeX: { equationNumbers: { autoNumber: 'AMS' } },"
                + "  displayAlign: 'left',"
                + "  displayIndent: '2em'"
                + "});"
                + "</script>"
                + "<script src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?"
                + "config=TeX-MML-AM_CHTML' async></script>"
                + "</head><body>"
                + html.toString()
                + "</body></html>";

        return htmlWithMathJax;
    }

    private void observe(){
        model.getAddQuestions().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean!=null && aBoolean){
                checkReadPermission(new OnCheckListener() {
                    @Override
                    public void onChecked(boolean isGranted) {
                        if (isGranted){
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"document/*");
                            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            startActivityForResult(intent, REQUEST_GALLERY_DOCUMENTS);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_GALLERY_DOCUMENTS && resultCode==RESULT_OK){
            showDialogWithEditText(data);
        }
    }

    private void showDialogWithEditText(Intent data) {
        if (dialog!=null){
            dialog.show();
            return;
        }
        dialog = new Dialog(requireContext());
        DialogEdittextBinding dBinding = DialogEdittextBinding.inflate(getLayoutInflater());
        dialog.setContentView(dBinding.getRoot());
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dBinding.positiveBtn.setOnClickListener(v -> {
            extractQuestion(data, dBinding.editTextDialog.getText().toString());
            dialog.dismiss();
        });

        dialog.show();
    }

    private void extractQuestion(Intent data, String page) {
        Log.d(TAG, "extractQuestion: data:"+data+" page:"+page);
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

        private final List<Question> list;

        public Adapter(List<Question> list){
            this.list = list;
        }

        @NonNull
        @Override
        public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerItemQuestionBinding binding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.recycler_item_question,parent,false);
            return new ViewHolder(binding);
        }

        @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
        @Override
        public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.binding.webView.getSettings().setJavaScriptEnabled(true);
            holder.binding.webView.loadDataWithBaseURL("file:///android_asset/", convertLatexToHtml(list.get(position).getQuestion()), "text/html", "UTF-8", "");
            holder.binding.webView.setOnTouchListener(new View.OnTouchListener() {
                float startX=0, startY=0;
                final int MAX_CLICK_DURATION = 200;
                final int MIN_LONG_CLICK_DURATION = 500;
                final int MAX_CLICK_DISTANCE = 15;
                long startClickTime=0;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getX();
                            startY = event.getY();
                            startClickTime = System.currentTimeMillis();
                            break;

                        case MotionEvent.ACTION_UP:
                            float endX = event.getX();
                            float endY = event.getY();
                            long clickDuration = System.currentTimeMillis() - startClickTime;
                            float dx = Math.abs(endX - startX);
                            float dy = Math.abs(endY - startY);
                            boolean isClick = clickDuration < MAX_CLICK_DURATION && dx < MAX_CLICK_DISTANCE && dy < MAX_CLICK_DISTANCE;
                            boolean isLongClick = clickDuration > MIN_LONG_CLICK_DURATION && dx < MAX_CLICK_DISTANCE && dy < MAX_CLICK_DISTANCE;

                            if (isClick) {
                                // Handle click event
                                if (anyOneSelected()){
                                    updateList(position);
                                }
                                return true; // Returning true indicates the event is consumed
                            }
                            if (isLongClick){
                                updateList(position);
                                return true;
                            }
                            break;
                    }
                    return false;
                }
            });
            int color;
            if (list.get(position).isSelected()){
                color = R.color.purple_200_30;
            }
            else{
                color = R.color.transparent;
            }
            holder.binding.root.setForeground(new ColorDrawable(ContextCompat.getColor(requireContext(),color)));

        }

        private boolean anyOneSelected() {
            for (Question q:list){
                if (q.isSelected()){
                    return true;
                }
            }
            return false;
        }

        private void updateList(int position) {
            list.get(position).setSelected(!list.get(position).isSelected());
            notifyItemChanged(position);
        }

        @Override
        public int getItemCount() {
            return list==null?0:list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final RecyclerItemQuestionBinding binding;
            public ViewHolder(@NonNull RecyclerItemQuestionBinding itemView) {
                super(itemView.getRoot());
                this.binding = itemView;
            }
        }
    }
}
