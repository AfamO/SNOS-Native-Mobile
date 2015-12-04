/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComboBox", com.codename1.ui.ComboBox.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("SpanLabel", com.codename1.components.SpanLabel.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Splash_Form";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComboBox", com.codename1.ui.ComboBox.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("SpanLabel", com.codename1.components.SpanLabel.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Splash_Form");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Button findPSAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("PSAboutBut", root);
    }

    public com.codename1.ui.Button findPSAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("PSAboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("PSAboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditConHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditConHomeBut", root);
    }

    public com.codename1.ui.Button findEditConHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditConHomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditConHomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMobLab(Component root) {
        return (com.codename1.ui.Label)findByName("MobLab", root);
    }

    public com.codename1.ui.Label findMobLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("MobLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("MobLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findNextContainer(Component root) {
        return (com.codename1.ui.Container)findByName("NextContainer", root);
    }

    public com.codename1.ui.Container findNextContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("NextContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("NextContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findLocTF(Component root) {
        return (com.codename1.ui.TextField)findByName("LocTF", root);
    }

    public com.codename1.ui.TextField findLocTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("LocTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("LocTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findPassLab(Component root) {
        return (com.codename1.ui.Label)findByName("PassLab", root);
    }

    public com.codename1.ui.Label findPassLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("PassLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("PassLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findPSHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("PSHomeBut", root);
    }

    public com.codename1.ui.Button findPSHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("PSHomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("PSHomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditLoginSetBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditLoginSetBut", root);
    }

    public com.codename1.ui.Button findEditLoginSetBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditLoginSetBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditLoginSetBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findJigawaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("JigawaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findJigawaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("JigawaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("JigawaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findBuildTypeTF(Component root) {
        return (com.codename1.ui.TextField)findByName("BuildTypeTF", root);
    }

    public com.codename1.ui.TextField findBuildTypeTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("BuildTypeTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("BuildTypeTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findEbonyiSpan(Component root) {
        return (com.codename1.components.SpanLabel)findByName("EbonyiSpan", root);
    }

    public com.codename1.components.SpanLabel findEbonyiSpan() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("EbonyiSpan", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("EbonyiSpan", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findRegHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("RegHomeBut", root);
    }

    public com.codename1.ui.Button findRegHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("RegHomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("RegHomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findStateTF(Component root) {
        return (com.codename1.ui.TextField)findByName("StateTF", root);
    }

    public com.codename1.ui.TextField findStateTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("StateTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("StateTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSSSTitleLab(Component root) {
        return (com.codename1.ui.Label)findByName("SSSTitleLab", root);
    }

    public com.codename1.ui.Label findSSSTitleLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("SSSTitleLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("SSSTitleLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTokenTF(Component root) {
        return (com.codename1.ui.TextField)findByName("TokenTF", root);
    }

    public com.codename1.ui.TextField findTokenTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("TokenTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("TokenTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAboutSettingBut(Component root) {
        return (com.codename1.ui.Button)findByName("AboutSettingBut", root);
    }

    public com.codename1.ui.Button findAboutSettingBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("AboutSettingBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("AboutSettingBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer11(Component root) {
        return (com.codename1.ui.Container)findByName("Container11", root);
    }

    public com.codename1.ui.Container findContainer11() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container11", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container11", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer10(Component root) {
        return (com.codename1.ui.Container)findByName("Container10", root);
    }

    public com.codename1.ui.Container findContainer10() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container10", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container10", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findContactSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("ContactSpanLab", root);
    }

    public com.codename1.components.SpanLabel findContactSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("ContactSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("ContactSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findLagosSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("LagosSpanLab", root);
    }

    public com.codename1.components.SpanLabel findLagosSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("LagosSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("LagosSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findTokenLab(Component root) {
        return (com.codename1.ui.Label)findByName("TokenLab", root);
    }

    public com.codename1.ui.Label findTokenLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("TokenLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("TokenLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findInBottomContainer(Component root) {
        return (com.codename1.ui.Container)findByName("InBottomContainer", root);
    }

    public com.codename1.ui.Container findInBottomContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("InBottomContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("InBottomContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findAddTA(Component root) {
        return (com.codename1.ui.TextArea)findByName("AddTA", root);
    }

    public com.codename1.ui.TextArea findAddTA() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("AddTA", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("AddTA", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findPixLab(Component root) {
        return (com.codename1.ui.Label)findByName("PixLab", root);
    }

    public com.codename1.ui.Label findPixLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("PixLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("PixLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findStatusContainer(Component root) {
        return (com.codename1.ui.Container)findByName("StatusContainer", root);
    }

    public com.codename1.ui.Container findStatusContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("StatusContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("StatusContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findVpassLab(Component root) {
        return (com.codename1.ui.Label)findByName("VpassLab", root);
    }

    public com.codename1.ui.Label findVpassLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("VpassLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("VpassLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findOldPassTF(Component root) {
        return (com.codename1.ui.TextField)findByName("OldPassTF", root);
    }

    public com.codename1.ui.TextField findOldPassTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("OldPassTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("OldPassTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findPSSetBut(Component root) {
        return (com.codename1.ui.Button)findByName("PSSetBut", root);
    }

    public com.codename1.ui.Button findPSSetBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("PSSetBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("PSSetBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findLoginBut(Component root) {
        return (com.codename1.ui.Button)findByName("LoginBut", root);
    }

    public com.codename1.ui.Button findLoginBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("LoginBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("LoginBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findMoreContactSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("MoreContactSpanLab", root);
    }

    public com.codename1.components.SpanLabel findMoreContactSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("MoreContactSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("MoreContactSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findAbiaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("AbiaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findAbiaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("AbiaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("AbiaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findNigerSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("NigerSpanLab", root);
    }

    public com.codename1.components.SpanLabel findNigerSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("NigerSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("NigerSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findAwkaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("AwkaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findAwkaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("AwkaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("AwkaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findResetEmailTF(Component root) {
        return (com.codename1.ui.TextField)findByName("ResetEmailTF", root);
    }

    public com.codename1.ui.TextField findResetEmailTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("ResetEmailTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("ResetEmailTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findPassTF(Component root) {
        return (com.codename1.ui.TextField)findByName("PassTF", root);
    }

    public com.codename1.ui.TextField findPassTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("PassTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("PassTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findStatusSpanab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("statusSpanab", root);
    }

    public com.codename1.components.SpanLabel findStatusSpanab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("statusSpanab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("statusSpanab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findRecoverBut(Component root) {
        return (com.codename1.ui.Button)findByName("RecoverBut", root);
    }

    public com.codename1.ui.Button findRecoverBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("RecoverBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("RecoverBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findConSettingsBut(Component root) {
        return (com.codename1.ui.Button)findByName("ConSettingsBut", root);
    }

    public com.codename1.ui.Button findConSettingsBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("ConSettingsBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("ConSettingsBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findOthernameTF(Component root) {
        return (com.codename1.ui.TextField)findByName("OthernameTF", root);
    }

    public com.codename1.ui.TextField findOthernameTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("OthernameTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("OthernameTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEdittContactButton(Component root) {
        return (com.codename1.ui.Button)findByName("EdittContactButton", root);
    }

    public com.codename1.ui.Button findEdittContactButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EdittContactButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EdittContactButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findAddTA2(Component root) {
        return (com.codename1.ui.TextArea)findByName("AddTA2", root);
    }

    public com.codename1.ui.TextArea findAddTA2() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("AddTA2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("AddTA2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findTarabaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("TarabaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findTarabaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("TarabaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("TarabaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findSSSContainer(Component root) {
        return (com.codename1.ui.Container)findByName("SSSContainer", root);
    }

    public com.codename1.ui.Container findSSSContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("SSSContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("SSSContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findVerifyButt(Component root) {
        return (com.codename1.ui.Button)findByName("VerifyButt", root);
    }

    public com.codename1.ui.Button findVerifyButt() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("VerifyButt", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("VerifyButt", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findEdoSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("EdoSpanLab", root);
    }

    public com.codename1.components.SpanLabel findEdoSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("EdoSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("EdoSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEmeSettingBut(Component root) {
        return (com.codename1.ui.Button)findByName("EmeSettingBut", root);
    }

    public com.codename1.ui.Button findEmeSettingBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EmeSettingBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EmeSettingBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAboutContactsBut(Component root) {
        return (com.codename1.ui.Button)findByName("AboutContactsBut", root);
    }

    public com.codename1.ui.Button findAboutContactsBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("AboutContactsBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("AboutContactsBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findAdamawaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("AdamawaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findAdamawaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("AdamawaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("AdamawaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContentContainer(Component root) {
        return (com.codename1.ui.Container)findByName("ContentContainer", root);
    }

    public com.codename1.ui.Container findContentContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("ContentContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("ContentContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel(Component root) {
        return (com.codename1.ui.Label)findByName("Label", root);
    }

    public com.codename1.ui.Label findLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEmeContactBut(Component root) {
        return (com.codename1.ui.Button)findByName("EmeContactBut", root);
    }

    public com.codename1.ui.Button findEmeContactBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EmeContactBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EmeContactBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findBottomContainer(Component root) {
        return (com.codename1.ui.Container)findByName("BottomContainer", root);
    }

    public com.codename1.ui.Container findBottomContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("BottomContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("BottomContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer4(Component root) {
        return (com.codename1.ui.Container)findByName("Container4", root);
    }

    public com.codename1.ui.Container findContainer4() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container4", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container4", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findDeltaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("DeltaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findDeltaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("DeltaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("DeltaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer3(Component root) {
        return (com.codename1.ui.Container)findByName("Container3", root);
    }

    public com.codename1.ui.Container findContainer3() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContentPan(Component root) {
        return (com.codename1.ui.Container)findByName("ContentPan", root);
    }

    public com.codename1.ui.Container findContentPan() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("ContentPan", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("ContentPan", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer8(Component root) {
        return (com.codename1.ui.Container)findByName("Container8", root);
    }

    public com.codename1.ui.Container findContainer8() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container8", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container8", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findBottomContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("BottomContainer1", root);
    }

    public com.codename1.ui.Container findBottomContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("BottomContainer1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("BottomContainer1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer7(Component root) {
        return (com.codename1.ui.Container)findByName("Container7", root);
    }

    public com.codename1.ui.Container findContainer7() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container7", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container7", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer6(Component root) {
        return (com.codename1.ui.Container)findByName("Container6", root);
    }

    public com.codename1.ui.Container findContainer6() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container6", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container6", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer5(Component root) {
        return (com.codename1.ui.Container)findByName("Container5", root);
    }

    public com.codename1.ui.Container findContainer5() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container5", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container5", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEmeHome(Component root) {
        return (com.codename1.ui.Button)findByName("EmeHome", root);
    }

    public com.codename1.ui.Button findEmeHome() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EmeHome", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EmeHome", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findAboutLab(Component root) {
        return (com.codename1.ui.Label)findByName("AboutLab", root);
    }

    public com.codename1.ui.Label findAboutLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("AboutLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("AboutLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findPSLoginBut(Component root) {
        return (com.codename1.ui.Button)findByName("PSLoginBut", root);
    }

    public com.codename1.ui.Button findPSLoginBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("PSLoginBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("PSLoginBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer9(Component root) {
        return (com.codename1.ui.Container)findByName("Container9", root);
    }

    public com.codename1.ui.Container findContainer9() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container9", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container9", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findGombeSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("GombeSpanLab", root);
    }

    public com.codename1.components.SpanLabel findGombeSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("GombeSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("GombeSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findContBut(Component root) {
        return (com.codename1.ui.Button)findByName("ContBut", root);
    }

    public com.codename1.ui.Button findContBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("ContBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("ContBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findRelaTF(Component root) {
        return (com.codename1.ui.TextField)findByName("RelaTF", root);
    }

    public com.codename1.ui.TextField findRelaTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("RelaTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("RelaTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findAddLab(Component root) {
        return (com.codename1.ui.Label)findByName("AddLab", root);
    }

    public com.codename1.ui.Label findAddLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("AddLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("AddLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAboutHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("AboutHomeBut", root);
    }

    public com.codename1.ui.Button findAboutHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("AboutHomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("AboutHomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findKwaraSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("KwaraSpanLab", root);
    }

    public com.codename1.components.SpanLabel findKwaraSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("KwaraSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("KwaraSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findPlateauSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("PlateauSpanLab", root);
    }

    public com.codename1.components.SpanLabel findPlateauSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("PlateauSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("PlateauSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findAboutSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("AboutSpanLab", root);
    }

    public com.codename1.components.SpanLabel findAboutSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("AboutSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("AboutSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findTokenLoginBut(Component root) {
        return (com.codename1.ui.Button)findByName("TokenLoginBut", root);
    }

    public com.codename1.ui.Button findTokenLoginBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("TokenLoginBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("TokenLoginBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findEkitiSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("EkitiSpanLab", root);
    }

    public com.codename1.components.SpanLabel findEkitiSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("EkitiSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("EkitiSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findRelaCB(Component root) {
        return (com.codename1.ui.ComboBox)findByName("RelaCB", root);
    }

    public com.codename1.ui.ComboBox findRelaCB() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("RelaCB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("RelaCB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findRegBut(Component root) {
        return (com.codename1.ui.Button)findByName("RegBut", root);
    }

    public com.codename1.ui.Button findRegBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("RegBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("RegBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findRelaLab(Component root) {
        return (com.codename1.ui.Label)findByName("RelaLab", root);
    }

    public com.codename1.ui.Label findRelaLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("RelaLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("RelaLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findLASMASpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("LASMASpanLab", root);
    }

    public com.codename1.components.SpanLabel findLASMASpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("LASMASpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("LASMASpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findNotLogginedBut(Component root) {
        return (com.codename1.ui.Button)findByName("NotLogginedBut", root);
    }

    public com.codename1.ui.Button findNotLogginedBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("NotLogginedBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("NotLogginedBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditConSettingsBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditConSettingsBut", root);
    }

    public com.codename1.ui.Button findEditConSettingsBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditConSettingsBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditConSettingsBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findEditTitleContainer(Component root) {
        return (com.codename1.ui.Container)findByName("EditTitleContainer", root);
    }

    public com.codename1.ui.Container findEditTitleContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("EditTitleContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("EditTitleContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findRegAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("RegAboutBut", root);
    }

    public com.codename1.ui.Button findRegAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("RegAboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("RegAboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSetBut(Component root) {
        return (com.codename1.ui.Button)findByName("SetBut", root);
    }

    public com.codename1.ui.Button findSetBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("SetBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("SetBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findHomeLoginBut(Component root) {
        return (com.codename1.ui.Button)findByName("HomeLoginBut", root);
    }

    public com.codename1.ui.Button findHomeLoginBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("HomeLoginBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("HomeLoginBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEmeBut(Component root) {
        return (com.codename1.ui.Button)findByName("EmeBut", root);
    }

    public com.codename1.ui.Button findEmeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EmeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EmeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findDevNameTF(Component root) {
        return (com.codename1.ui.TextField)findByName("DevNameTF", root);
    }

    public com.codename1.ui.TextField findDevNameTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("DevNameTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("DevNameTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findLgaTF(Component root) {
        return (com.codename1.ui.TextField)findByName("LgaTF", root);
    }

    public com.codename1.ui.TextField findLgaTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("LgaTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("LgaTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findButtonContainer(Component root) {
        return (com.codename1.ui.Container)findByName("ButtonContainer", root);
    }

    public com.codename1.ui.Container findButtonContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("ButtonContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("ButtonContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLgaLab(Component root) {
        return (com.codename1.ui.Label)findByName("LgaLab", root);
    }

    public com.codename1.ui.Label findLgaLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("LgaLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("LgaLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditLoginBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditLoginBut", root);
    }

    public com.codename1.ui.Button findEditLoginBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditLoginBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditLoginBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findIGPSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("IGPSpanLab", root);
    }

    public com.codename1.components.SpanLabel findIGPSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("IGPSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("IGPSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findPoliceContentContainer(Component root) {
        return (com.codename1.ui.Container)findByName("PoliceContentContainer", root);
    }

    public com.codename1.ui.Container findPoliceContentContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("PoliceContentContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("PoliceContentContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findSpanLabel(Component root) {
        return (com.codename1.components.SpanLabel)findByName("SpanLabel", root);
    }

    public com.codename1.components.SpanLabel findSpanLabel() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("SpanLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("SpanLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findConHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("ConHomeBut", root);
    }

    public com.codename1.ui.Button findConHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("ConHomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("ConHomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findFctSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("FctSpanLab", root);
    }

    public com.codename1.components.SpanLabel findFctSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("FctSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("FctSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditLoginAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditLoginAboutBut", root);
    }

    public com.codename1.ui.Button findEditLoginAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditLoginAboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditLoginAboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findMobTF(Component root) {
        return (com.codename1.ui.TextField)findByName("MobTF", root);
    }

    public com.codename1.ui.TextField findMobTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("MobTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("MobTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findDeviceOnOffSwitch(Component root) {
        return (com.codename1.ui.ComboBox)findByName("DeviceOnOffSwitch", root);
    }

    public com.codename1.ui.ComboBox findDeviceOnOffSwitch() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("DeviceOnOffSwitch", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("DeviceOnOffSwitch", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findBaySpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("BaySpanLab", root);
    }

    public com.codename1.components.SpanLabel findBaySpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("BaySpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("BaySpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findLgaCB(Component root) {
        return (com.codename1.ui.ComboBox)findByName("LgaCB", root);
    }

    public com.codename1.ui.ComboBox findLgaCB() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("LgaCB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("LgaCB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findDevBut(Component root) {
        return (com.codename1.ui.Button)findByName("DevBut", root);
    }

    public com.codename1.ui.Button findDevBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("DevBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("DevBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findRiversSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("RiversSpanLab", root);
    }

    public com.codename1.components.SpanLabel findRiversSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("RiversSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("RiversSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findTopContainer(Component root) {
        return (com.codename1.ui.Container)findByName("TopContainer", root);
    }

    public com.codename1.ui.Container findTopContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("TopContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("TopContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditRegAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditRegAboutBut", root);
    }

    public com.codename1.ui.Button findEditRegAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditRegAboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditRegAboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findInTitleContainer(Component root) {
        return (com.codename1.ui.Container)findByName("InTitleContainer", root);
    }

    public com.codename1.ui.Container findInTitleContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("InTitleContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("InTitleContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findZamfaraSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("ZamfaraSpanLab", root);
    }

    public com.codename1.components.SpanLabel findZamfaraSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("ZamfaraSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("ZamfaraSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findNameLab(Component root) {
        return (com.codename1.ui.Label)findByName("NameLab", root);
    }

    public com.codename1.ui.Label findNameLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("NameLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("NameLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findKanoSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("KanoSpanLab", root);
    }

    public com.codename1.components.SpanLabel findKanoSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("KanoSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("KanoSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findKatsinaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("KatsinaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findKatsinaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("KatsinaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("KatsinaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findConAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("ConAboutBut", root);
    }

    public com.codename1.ui.Button findConAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("ConAboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("ConAboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSpaceLab(Component root) {
        return (com.codename1.ui.Label)findByName("SpaceLab", root);
    }

    public com.codename1.ui.Label findSpaceLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("SpaceLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("SpaceLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findSNOCContainer(Component root) {
        return (com.codename1.ui.Container)findByName("SNOCContainer", root);
    }

    public com.codename1.ui.Container findSNOCContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("SNOCContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("SNOCContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findVpassTF(Component root) {
        return (com.codename1.ui.TextField)findByName("VpassTF", root);
    }

    public com.codename1.ui.TextField findVpassTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("VpassTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("VpassTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findOldPassLab(Component root) {
        return (com.codename1.ui.Label)findByName("OldPassLab", root);
    }

    public com.codename1.ui.Label findOldPassLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("OldPassLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("OldPassLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSaveButt(Component root) {
        return (com.codename1.ui.Button)findByName("SaveButt", root);
    }

    public com.codename1.ui.Button findSaveButt() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("SaveButt", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("SaveButt", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findKadunaSpan(Component root) {
        return (com.codename1.components.SpanLabel)findByName("KadunaSpan", root);
    }

    public com.codename1.components.SpanLabel findKadunaSpan() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("KadunaSpan", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("KadunaSpan", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findTopSpaceLab(Component root) {
        return (com.codename1.ui.Label)findByName("TopSpaceLab", root);
    }

    public com.codename1.ui.Label findTopSpaceLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("TopSpaceLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("TopSpaceLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findSNOCTitle(Component root) {
        return (com.codename1.ui.Container)findByName("SNOCTitle", root);
    }

    public com.codename1.ui.Container findSNOCTitle() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("SNOCTitle", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("SNOCTitle", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findSSSContentContainer(Component root) {
        return (com.codename1.ui.Container)findByName("SSSContentContainer", root);
    }

    public com.codename1.ui.Container findSSSContentContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("SSSContentContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("SSSContentContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditLoginEmeBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditLoginEmeBut", root);
    }

    public com.codename1.ui.Button findEditLoginEmeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditLoginEmeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditLoginEmeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLogLab(Component root) {
        return (com.codename1.ui.Label)findByName("logLab", root);
    }

    public com.codename1.ui.Label findLogLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("logLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("logLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findPSEmeBut(Component root) {
        return (com.codename1.ui.Button)findByName("PSEmeBut", root);
    }

    public com.codename1.ui.Button findPSEmeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("PSEmeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("PSEmeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findPassRecoverUserTF(Component root) {
        return (com.codename1.ui.TextField)findByName("PassRecoverUserTF", root);
    }

    public com.codename1.ui.TextField findPassRecoverUserTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("PassRecoverUserTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("PassRecoverUserTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findFRSCSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("FRSCSpanLab", root);
    }

    public com.codename1.components.SpanLabel findFRSCSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("FRSCSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("FRSCSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findEditRegContentContainer(Component root) {
        return (com.codename1.ui.Container)findByName("EditRegContentContainer", root);
    }

    public com.codename1.ui.Container findEditRegContentContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("EditRegContentContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("EditRegContentContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findPixContainer(Component root) {
        return (com.codename1.ui.Container)findByName("PixContainer", root);
    }

    public com.codename1.ui.Container findPixContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("PixContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("PixContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findYobeSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("YobeSpanLab", root);
    }

    public com.codename1.components.SpanLabel findYobeSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("YobeSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("YobeSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findEnuguSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("EnuguSpanLab", root);
    }

    public com.codename1.components.SpanLabel findEnuguSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("EnuguSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("EnuguSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("HomeBut", root);
    }

    public com.codename1.ui.Button findHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("HomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("HomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findInContentContainer(Component root) {
        return (com.codename1.ui.Container)findByName("InContentContainer", root);
    }

    public com.codename1.ui.Container findInContentContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("InContentContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("InContentContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findDesLab(Component root) {
        return (com.codename1.ui.Label)findByName("DesLab", root);
    }

    public com.codename1.ui.Label findDesLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("DesLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("DesLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findImoSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("ImoSpanLab", root);
    }

    public com.codename1.components.SpanLabel findImoSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("ImoSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("ImoSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findPolicContainer(Component root) {
        return (com.codename1.ui.Container)findByName("PolicContainer", root);
    }

    public com.codename1.ui.Container findPolicContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("PolicContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("PolicContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findOgunSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("OgunSpanLab", root);
    }

    public com.codename1.components.SpanLabel findOgunSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("OgunSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("OgunSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEmeAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("EmeAboutBut", root);
    }

    public com.codename1.ui.Button findEmeAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EmeAboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EmeAboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSapLab(Component root) {
        return (com.codename1.ui.Label)findByName("SapLab", root);
    }

    public com.codename1.ui.Label findSapLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("SapLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("SapLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findStaCB(Component root) {
        return (com.codename1.ui.ComboBox)findByName("StaCB", root);
    }

    public com.codename1.ui.ComboBox findStaCB() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("StaCB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("StaCB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStaLab(Component root) {
        return (com.codename1.ui.Label)findByName("StaLab", root);
    }

    public com.codename1.ui.Label findStaLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("StaLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("StaLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findFirstnameTF(Component root) {
        return (com.codename1.ui.TextField)findByName("FirstnameTF", root);
    }

    public com.codename1.ui.TextField findFirstnameTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("FirstnameTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("FirstnameTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findFirstLab(Component root) {
        return (com.codename1.ui.Label)findByName("FirstLab", root);
    }

    public com.codename1.ui.Label findFirstLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("FirstLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("FirstLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditConContactBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditConContactBut", root);
    }

    public com.codename1.ui.Button findEditConContactBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditConContactBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditConContactBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findSokotoSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("SokotoSpanLab", root);
    }

    public com.codename1.components.SpanLabel findSokotoSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("SokotoSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("SokotoSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditRegHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditRegHomeBut", root);
    }

    public com.codename1.ui.Button findEditRegHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditRegHomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditRegHomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditRegBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditRegBut", root);
    }

    public com.codename1.ui.Button findEditRegBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditRegBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditRegBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findHomeFutBut(Component root) {
        return (com.codename1.ui.Button)findByName("HomeFutBut", root);
    }

    public com.codename1.ui.Button findHomeFutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("HomeFutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("HomeFutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findOyoSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("OyoSpanLab", root);
    }

    public com.codename1.components.SpanLabel findOyoSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("OyoSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("OyoSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findDesTA(Component root) {
        return (com.codename1.ui.TextArea)findByName("DesTA", root);
    }

    public com.codename1.ui.TextArea findDesTA() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("DesTA", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("DesTA", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findSNOCSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("SNOCSpanLab", root);
    }

    public com.codename1.components.SpanLabel findSNOCSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("SNOCSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("SNOCSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findInnerContainer(Component root) {
        return (com.codename1.ui.Container)findByName("InnerContainer", root);
    }

    public com.codename1.ui.Container findInnerContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("InnerContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("InnerContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findAnambraSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("AnambraSpanLab", root);
    }

    public com.codename1.components.SpanLabel findAnambraSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("AnambraSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("AnambraSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findInBottonContainer(Component root) {
        return (com.codename1.ui.Container)findByName("InBottonContainer", root);
    }

    public com.codename1.ui.Container findInBottonContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("InBottonContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("InBottonContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findPreBut(Component root) {
        return (com.codename1.ui.Button)findByName("PreBut", root);
    }

    public com.codename1.ui.Button findPreBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("PreBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("PreBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findInnerContentContainer(Component root) {
        return (com.codename1.ui.Container)findByName("InnerContentContainer", root);
    }

    public com.codename1.ui.Container findInnerContentContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("InnerContentContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("InnerContentContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findTitleContainer(Component root) {
        return (com.codename1.ui.Container)findByName("TitleContainer", root);
    }

    public com.codename1.ui.Container findTitleContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("TitleContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("TitleContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditRegSetBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditRegSetBut", root);
    }

    public com.codename1.ui.Button findEditRegSetBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditRegSetBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditRegSetBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSignedUpBut(Component root) {
        return (com.codename1.ui.Button)findByName("SignedUpBut", root);
    }

    public com.codename1.ui.Button findSignedUpBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("SignedUpBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("SignedUpBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditLoginHomeBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditLoginHomeBut", root);
    }

    public com.codename1.ui.Button findEditLoginHomeBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditLoginHomeBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditLoginHomeBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findUserTF(Component root) {
        return (com.codename1.ui.TextField)findByName("UserTF", root);
    }

    public com.codename1.ui.TextField findUserTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("UserTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("UserTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findEmailLab(Component root) {
        return (com.codename1.ui.Label)findByName("EmailLab", root);
    }

    public com.codename1.ui.Label findEmailLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("EmailLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("EmailLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findBauchiSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("BauchiSpanLab", root);
    }

    public com.codename1.components.SpanLabel findBauchiSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("BauchiSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("BauchiSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findEmeTitleLab(Component root) {
        return (com.codename1.ui.Label)findByName("EmeTitleLab", root);
    }

    public com.codename1.ui.Label findEmeTitleLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("EmeTitleLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("EmeTitleLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStatusLab(Component root) {
        return (com.codename1.ui.Label)findByName("StatusLab", root);
    }

    public com.codename1.ui.Label findStatusLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("StatusLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("StatusLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findContactsOnOffSwitch(Component root) {
        return (com.codename1.ui.ComboBox)findByName("ContactsOnOffSwitch", root);
    }

    public com.codename1.ui.ComboBox findContactsOnOffSwitch() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("ContactsOnOffSwitch", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("ContactsOnOffSwitch", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditRegContBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditRegContBut", root);
    }

    public com.codename1.ui.Button findEditRegContBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditRegContBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditRegContBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findEmeLab(Component root) {
        return (com.codename1.ui.Label)findByName("EmeLab", root);
    }

    public com.codename1.ui.Label findEmeLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("EmeLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("EmeLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findKogiSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("KogiSpanLab", root);
    }

    public com.codename1.components.SpanLabel findKogiSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("KogiSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("KogiSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findOsunSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("OsunSpanLab", root);
    }

    public com.codename1.components.SpanLabel findOsunSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("OsunSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("OsunSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findEmailTF(Component root) {
        return (com.codename1.ui.TextField)findByName("EmailTF", root);
    }

    public com.codename1.ui.TextField findEmailTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("EmailTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("EmailTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findBuildTypeLab(Component root) {
        return (com.codename1.ui.Label)findByName("BuildTypeLab", root);
    }

    public com.codename1.ui.Label findBuildTypeLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("BuildTypeLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("BuildTypeLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findBenueSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("BenueSpanLab", root);
    }

    public com.codename1.components.SpanLabel findBenueSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("BenueSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("BenueSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findNasarwaSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("NasarwaSpanLab", root);
    }

    public com.codename1.components.SpanLabel findNasarwaSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("NasarwaSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("NasarwaSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findImageContainer(Component root) {
        return (com.codename1.ui.Container)findByName("ImageContainer", root);
    }

    public com.codename1.ui.Container findImageContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("ImageContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("ImageContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findEditConAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("EditConAboutBut", root);
    }

    public com.codename1.ui.Button findEditConAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("EditConAboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("EditConAboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findCrossSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("CrossSpanLab", root);
    }

    public com.codename1.components.SpanLabel findCrossSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("CrossSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("CrossSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAboutBut(Component root) {
        return (com.codename1.ui.Button)findByName("AboutBut", root);
    }

    public com.codename1.ui.Button findAboutBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("AboutBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("AboutBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findRegContBut(Component root) {
        return (com.codename1.ui.Button)findByName("RegContBut", root);
    }

    public com.codename1.ui.Button findRegContBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("RegContBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("RegContBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findRegSetBut(Component root) {
        return (com.codename1.ui.Button)findByName("RegSetBut", root);
    }

    public com.codename1.ui.Button findRegSetBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("RegSetBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("RegSetBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findOndoSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("OndoSpanLab", root);
    }

    public com.codename1.components.SpanLabel findOndoSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("OndoSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("OndoSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNameTF(Component root) {
        return (com.codename1.ui.TextField)findByName("NameTF", root);
    }

    public com.codename1.ui.TextField findNameTF() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("NameTF", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("NameTF", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findBornoSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("BornoSpanLab", root);
    }

    public com.codename1.components.SpanLabel findBornoSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("BornoSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("BornoSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLocLab(Component root) {
        return (com.codename1.ui.Label)findByName("LocLab", root);
    }

    public com.codename1.ui.Label findLocLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("LocLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("LocLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findKebbiSpanLab(Component root) {
        return (com.codename1.components.SpanLabel)findByName("KebbiSpanLab", root);
    }

    public com.codename1.components.SpanLabel findKebbiSpanLab() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("KebbiSpanLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("KebbiSpanLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findHomeRegBut(Component root) {
        return (com.codename1.ui.Button)findByName("HomeRegBut", root);
    }

    public com.codename1.ui.Button findHomeRegBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("HomeRegBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("HomeRegBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findUserLab(Component root) {
        return (com.codename1.ui.Label)findByName("UserLab", root);
    }

    public com.codename1.ui.Label findUserLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("UserLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("UserLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findConContactBut(Component root) {
        return (com.codename1.ui.Button)findByName("ConContactBut", root);
    }

    public com.codename1.ui.Button findConContactBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("ConContactBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("ConContactBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findNextBut(Component root) {
        return (com.codename1.ui.Button)findByName("NextBut", root);
    }

    public com.codename1.ui.Button findNextBut() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("NextBut", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("NextBut", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMidLab(Component root) {
        return (com.codename1.ui.Label)findByName("MidLab", root);
    }

    public com.codename1.ui.Label findMidLab() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("MidLab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("MidLab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public static final int COMMAND_ContactsFormAboutSNOS = 37;
    public static final int COMMAND_PasswordRecoveryEmergencyContacts = 65;
    public static final int COMMAND_AboutFormSettings = 56;
    public static final int COMMAND_RegFormSettings = 3;
    public static final int COMMAND_ContactsFormEmergecyContacts = 36;
    public static final int COMMAND_AlertFormLogout = 55;
    public static final int COMMAND_DeviceFormAboutSNOS = 33;
    public static final int COMMAND_AboutFormEmergecyContacts = 40;
    public static final int COMMAND_DeviceFormEmergencyContacts = 32;
    public static final int COMMAND_LoginFormAboutSNOS = 93;
    public static final int COMMAND_AlertFormChangeLoginDetails = 84;
    public static final int COMMAND_AlertFormAboutSNOS = 52;
    public static final int COMMAND_AlertFormContacts = 82;
    public static final int COMMAND_EditContactFormChangeLoginDetails = 69;
    public static final int COMMAND_EditDeviceFormProfile = 71;
    public static final int COMMAND_EditRegInformFormSettings = 30;
    public static final int COMMAND_EditContactFormDevice = 68;
    public static final int COMMAND_VerifyTokenAboutSNOS = 85;
    public static final int COMMAND_EditDeviceFormEmergencyContacts = 45;
    public static final int COMMAND_DeviceFormSettings = 34;
    public static final int COMMAND_AlertFormDevice = 83;
    public static final int COMMAND_EditLoginEmergencyContacts = 63;
    public static final int COMMAND_EditDeviceFormAboutSNOS = 46;
    public static final int COMMAND_EditDeviceFormInbox = 73;
    public static final int COMMAND_ContactsFormSettings = 38;
    public static final int COMMAND_EditRegInformFormAboutSNOS = 29;
    public static final int COMMAND_EditRegInformFormDevice = 77;
    public static final int COMMAND_EmergencyContactsInformFormSettings = 49;
    public static final int COMMAND_RegFormAboutSNOS = 2;
    public static final int COMMAND_VerifyTokenEmergencyContacts = 86;
    public static final int COMMAND_ResetLoginDetailsAboutSNOS = 87;
    public static final int COMMAND_EditContactFormEmergencyContacts = 48;
    public static final int COMMAND_EditLoginLogout = 57;
    public static final int COMMAND_EditLoginContacts = 60;
    public static final int COMMAND_EditContactFormProfile = 67;
    public static final int COMMAND_RegFormEmergencyContacts = 1;
    public static final int COMMAND_EditContactFormSettings = 51;
    public static final int COMMAND_SplashFormAboutSNOS = 90;
    public static final int COMMAND_EditDeviceFormSettings = 50;
    public static final int COMMAND_ResetLoginDetailsEmergencyContacts = 88;
    public static final int COMMAND_AlertFormProfile = 54;
    public static final int COMMAND_EditContactFormAboutSNOS = 47;
    public static final int COMMAND_EditRegInformFormInbox = 78;
    public static final int COMMAND_SplashFormExit = 91;
    public static final int COMMAND_EditContactFormLogout = 70;
    public static final int COMMAND_EditLoginDevice = 61;
    public static final int COMMAND_EditRegInformFormLogout = 79;
    public static final int COMMAND_EditLoginInbox = 58;
    public static final int COMMAND_EditRegInformFormContacts = 76;
    public static final int COMMAND_EditRegInformFormChangeLoginDetails = 80;
    public static final int COMMAND_EditDeviceFormLogout = 74;
    public static final int COMMAND_EditDeviceFormContacts = 72;
    public static final int COMMAND_AlertFormEmergencyContacts = 53;
    public static final int COMMAND_EditLoginProfile = 59;
    public static final int COMMAND_LoginFormHome = 92;
    public static final int COMMAND_PasswordRecoveryAboutSNOS = 64;
    public static final int COMMAND_LoginFormExit = 95;
    public static final int COMMAND_EditContactFormInbox = 66;
    public static final int COMMAND_SplashFormEmergencyContacts = 89;
    public static final int COMMAND_EditDeviceFormChangeLoginDetails = 75;
    public static final int COMMAND_EditLoginAboutSNOS = 62;
    public static final int COMMAND_LoginFormEmergencyContacts = 94;

    protected boolean onContactsFormAboutSNOS() {
        return false;
    }

    protected boolean onPasswordRecoveryEmergencyContacts() {
        return false;
    }

    protected boolean onAboutFormSettings() {
        return false;
    }

    protected boolean onRegFormSettings() {
        return false;
    }

    protected boolean onContactsFormEmergecyContacts() {
        return false;
    }

    protected boolean onAlertFormLogout() {
        return false;
    }

    protected boolean onDeviceFormAboutSNOS() {
        return false;
    }

    protected boolean onAboutFormEmergecyContacts() {
        return false;
    }

    protected boolean onDeviceFormEmergencyContacts() {
        return false;
    }

    protected boolean onLoginFormAboutSNOS() {
        return false;
    }

    protected boolean onAlertFormChangeLoginDetails() {
        return false;
    }

    protected boolean onAlertFormAboutSNOS() {
        return false;
    }

    protected boolean onAlertFormContacts() {
        return false;
    }

    protected boolean onEditContactFormChangeLoginDetails() {
        return false;
    }

    protected boolean onEditDeviceFormProfile() {
        return false;
    }

    protected boolean onEditRegInformFormSettings() {
        return false;
    }

    protected boolean onEditContactFormDevice() {
        return false;
    }

    protected boolean onVerifyTokenAboutSNOS() {
        return false;
    }

    protected boolean onEditDeviceFormEmergencyContacts() {
        return false;
    }

    protected boolean onDeviceFormSettings() {
        return false;
    }

    protected boolean onAlertFormDevice() {
        return false;
    }

    protected boolean onEditLoginEmergencyContacts() {
        return false;
    }

    protected boolean onEditDeviceFormAboutSNOS() {
        return false;
    }

    protected boolean onEditDeviceFormInbox() {
        return false;
    }

    protected boolean onContactsFormSettings() {
        return false;
    }

    protected boolean onEditRegInformFormAboutSNOS() {
        return false;
    }

    protected boolean onEditRegInformFormDevice() {
        return false;
    }

    protected boolean onEmergencyContactsInformFormSettings() {
        return false;
    }

    protected boolean onRegFormAboutSNOS() {
        return false;
    }

    protected boolean onVerifyTokenEmergencyContacts() {
        return false;
    }

    protected boolean onResetLoginDetailsAboutSNOS() {
        return false;
    }

    protected boolean onEditContactFormEmergencyContacts() {
        return false;
    }

    protected boolean onEditLoginLogout() {
        return false;
    }

    protected boolean onEditLoginContacts() {
        return false;
    }

    protected boolean onEditContactFormProfile() {
        return false;
    }

    protected boolean onRegFormEmergencyContacts() {
        return false;
    }

    protected boolean onEditContactFormSettings() {
        return false;
    }

    protected boolean onSplashFormAboutSNOS() {
        return false;
    }

    protected boolean onEditDeviceFormSettings() {
        return false;
    }

    protected boolean onResetLoginDetailsEmergencyContacts() {
        return false;
    }

    protected boolean onAlertFormProfile() {
        return false;
    }

    protected boolean onEditContactFormAboutSNOS() {
        return false;
    }

    protected boolean onEditRegInformFormInbox() {
        return false;
    }

    protected boolean onSplashFormExit() {
        return false;
    }

    protected boolean onEditContactFormLogout() {
        return false;
    }

    protected boolean onEditLoginDevice() {
        return false;
    }

    protected boolean onEditRegInformFormLogout() {
        return false;
    }

    protected boolean onEditLoginInbox() {
        return false;
    }

    protected boolean onEditRegInformFormContacts() {
        return false;
    }

    protected boolean onEditRegInformFormChangeLoginDetails() {
        return false;
    }

    protected boolean onEditDeviceFormLogout() {
        return false;
    }

    protected boolean onEditDeviceFormContacts() {
        return false;
    }

    protected boolean onAlertFormEmergencyContacts() {
        return false;
    }

    protected boolean onEditLoginProfile() {
        return false;
    }

    protected boolean onLoginFormHome() {
        return false;
    }

    protected boolean onPasswordRecoveryAboutSNOS() {
        return false;
    }

    protected boolean onLoginFormExit() {
        return false;
    }

    protected boolean onEditContactFormInbox() {
        return false;
    }

    protected boolean onSplashFormEmergencyContacts() {
        return false;
    }

    protected boolean onEditDeviceFormChangeLoginDetails() {
        return false;
    }

    protected boolean onEditLoginAboutSNOS() {
        return false;
    }

    protected boolean onLoginFormEmergencyContacts() {
        return false;
    }

    protected void processCommand(ActionEvent ev, Command cmd) {
        switch(cmd.getId()) {
            case COMMAND_ContactsFormAboutSNOS:
                if(onContactsFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_PasswordRecoveryEmergencyContacts:
                if(onPasswordRecoveryEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AboutFormSettings:
                if(onAboutFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_RegFormSettings:
                if(onRegFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ContactsFormEmergecyContacts:
                if(onContactsFormEmergecyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AlertFormLogout:
                if(onAlertFormLogout()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_DeviceFormAboutSNOS:
                if(onDeviceFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AboutFormEmergecyContacts:
                if(onAboutFormEmergecyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_DeviceFormEmergencyContacts:
                if(onDeviceFormEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoginFormAboutSNOS:
                if(onLoginFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AlertFormChangeLoginDetails:
                if(onAlertFormChangeLoginDetails()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AlertFormAboutSNOS:
                if(onAlertFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AlertFormContacts:
                if(onAlertFormContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormChangeLoginDetails:
                if(onEditContactFormChangeLoginDetails()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormProfile:
                if(onEditDeviceFormProfile()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditRegInformFormSettings:
                if(onEditRegInformFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormDevice:
                if(onEditContactFormDevice()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_VerifyTokenAboutSNOS:
                if(onVerifyTokenAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormEmergencyContacts:
                if(onEditDeviceFormEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_DeviceFormSettings:
                if(onDeviceFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AlertFormDevice:
                if(onAlertFormDevice()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditLoginEmergencyContacts:
                if(onEditLoginEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormAboutSNOS:
                if(onEditDeviceFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormInbox:
                if(onEditDeviceFormInbox()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ContactsFormSettings:
                if(onContactsFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditRegInformFormAboutSNOS:
                if(onEditRegInformFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditRegInformFormDevice:
                if(onEditRegInformFormDevice()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EmergencyContactsInformFormSettings:
                if(onEmergencyContactsInformFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_RegFormAboutSNOS:
                if(onRegFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_VerifyTokenEmergencyContacts:
                if(onVerifyTokenEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ResetLoginDetailsAboutSNOS:
                if(onResetLoginDetailsAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormEmergencyContacts:
                if(onEditContactFormEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditLoginLogout:
                if(onEditLoginLogout()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditLoginContacts:
                if(onEditLoginContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormProfile:
                if(onEditContactFormProfile()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_RegFormEmergencyContacts:
                if(onRegFormEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormSettings:
                if(onEditContactFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_SplashFormAboutSNOS:
                if(onSplashFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormSettings:
                if(onEditDeviceFormSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ResetLoginDetailsEmergencyContacts:
                if(onResetLoginDetailsEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AlertFormProfile:
                if(onAlertFormProfile()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormAboutSNOS:
                if(onEditContactFormAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditRegInformFormInbox:
                if(onEditRegInformFormInbox()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_SplashFormExit:
                if(onSplashFormExit()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormLogout:
                if(onEditContactFormLogout()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditLoginDevice:
                if(onEditLoginDevice()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditRegInformFormLogout:
                if(onEditRegInformFormLogout()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditLoginInbox:
                if(onEditLoginInbox()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditRegInformFormContacts:
                if(onEditRegInformFormContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditRegInformFormChangeLoginDetails:
                if(onEditRegInformFormChangeLoginDetails()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormLogout:
                if(onEditDeviceFormLogout()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormContacts:
                if(onEditDeviceFormContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AlertFormEmergencyContacts:
                if(onAlertFormEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditLoginProfile:
                if(onEditLoginProfile()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoginFormHome:
                if(onLoginFormHome()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_PasswordRecoveryAboutSNOS:
                if(onPasswordRecoveryAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoginFormExit:
                if(onLoginFormExit()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditContactFormInbox:
                if(onEditContactFormInbox()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_SplashFormEmergencyContacts:
                if(onSplashFormEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditDeviceFormChangeLoginDetails:
                if(onEditDeviceFormChangeLoginDetails()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_EditLoginAboutSNOS:
                if(onEditLoginAboutSNOS()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoginFormEmergencyContacts:
                if(onLoginFormEmergencyContacts()) {
                    ev.consume();
                    return;
                }
                break;

        }
        if(ev.getComponent() != null) {
            handleComponentAction(ev.getComponent(), ev);
        }
    }

    protected void exitForm(Form f) {
        if("Password_Recovery".equals(f.getName())) {
            exitPasswordRecovery(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Login".equals(f.getName())) {
            exitEditLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VerifyToken".equals(f.getName())) {
            exitVerifyToken(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Alert_Form".equals(f.getName())) {
            exitAlertForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("About_Form".equals(f.getName())) {
            exitAboutForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Device_Form".equals(f.getName())) {
            exitDeviceForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Contact_Form".equals(f.getName())) {
            exitEditContactForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Emergency_Contacts_Inform_Form".equals(f.getName())) {
            exitEmergencyContactsInformForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash_Form".equals(f.getName())) {
            exitSplashForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Device_Form".equals(f.getName())) {
            exitEditDeviceForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Reg_Inform_Form".equals(f.getName())) {
            exitEditRegInformForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login_Form".equals(f.getName())) {
            exitLoginForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts_Form".equals(f.getName())) {
            exitContactsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reg_Form".equals(f.getName())) {
            exitRegForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reset_LoginDetails".equals(f.getName())) {
            exitResetLoginDetails(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitPasswordRecovery(Form f) {
    }


    protected void exitEditLogin(Form f) {
    }


    protected void exitVerifyToken(Form f) {
    }


    protected void exitAlertForm(Form f) {
    }


    protected void exitAboutForm(Form f) {
    }


    protected void exitDeviceForm(Form f) {
    }


    protected void exitEditContactForm(Form f) {
    }


    protected void exitEmergencyContactsInformForm(Form f) {
    }


    protected void exitSplashForm(Form f) {
    }


    protected void exitEditDeviceForm(Form f) {
    }


    protected void exitEditRegInformForm(Form f) {
    }


    protected void exitLoginForm(Form f) {
    }


    protected void exitContactsForm(Form f) {
    }


    protected void exitRegForm(Form f) {
    }


    protected void exitResetLoginDetails(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("Password_Recovery".equals(f.getName())) {
            beforePasswordRecovery(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Login".equals(f.getName())) {
            beforeEditLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VerifyToken".equals(f.getName())) {
            beforeVerifyToken(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Alert_Form".equals(f.getName())) {
            beforeAlertForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("About_Form".equals(f.getName())) {
            beforeAboutForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Device_Form".equals(f.getName())) {
            beforeDeviceForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Contact_Form".equals(f.getName())) {
            beforeEditContactForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Emergency_Contacts_Inform_Form".equals(f.getName())) {
            beforeEmergencyContactsInformForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash_Form".equals(f.getName())) {
            beforeSplashForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Device_Form".equals(f.getName())) {
            beforeEditDeviceForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Reg_Inform_Form".equals(f.getName())) {
            beforeEditRegInformForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login_Form".equals(f.getName())) {
            beforeLoginForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts_Form".equals(f.getName())) {
            beforeContactsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reg_Form".equals(f.getName())) {
            beforeRegForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reset_LoginDetails".equals(f.getName())) {
            beforeResetLoginDetails(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforePasswordRecovery(Form f) {
    }


    protected void beforeEditLogin(Form f) {
    }


    protected void beforeVerifyToken(Form f) {
    }


    protected void beforeAlertForm(Form f) {
    }


    protected void beforeAboutForm(Form f) {
    }


    protected void beforeDeviceForm(Form f) {
    }


    protected void beforeEditContactForm(Form f) {
    }


    protected void beforeEmergencyContactsInformForm(Form f) {
    }


    protected void beforeSplashForm(Form f) {
    }


    protected void beforeEditDeviceForm(Form f) {
    }


    protected void beforeEditRegInformForm(Form f) {
    }


    protected void beforeLoginForm(Form f) {
    }


    protected void beforeContactsForm(Form f) {
    }


    protected void beforeRegForm(Form f) {
    }


    protected void beforeResetLoginDetails(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("Password_Recovery".equals(c.getName())) {
            beforeContainerPasswordRecovery(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Login".equals(c.getName())) {
            beforeContainerEditLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VerifyToken".equals(c.getName())) {
            beforeContainerVerifyToken(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Alert_Form".equals(c.getName())) {
            beforeContainerAlertForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("About_Form".equals(c.getName())) {
            beforeContainerAboutForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Device_Form".equals(c.getName())) {
            beforeContainerDeviceForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Contact_Form".equals(c.getName())) {
            beforeContainerEditContactForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Emergency_Contacts_Inform_Form".equals(c.getName())) {
            beforeContainerEmergencyContactsInformForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash_Form".equals(c.getName())) {
            beforeContainerSplashForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Device_Form".equals(c.getName())) {
            beforeContainerEditDeviceForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Reg_Inform_Form".equals(c.getName())) {
            beforeContainerEditRegInformForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login_Form".equals(c.getName())) {
            beforeContainerLoginForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts_Form".equals(c.getName())) {
            beforeContainerContactsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reg_Form".equals(c.getName())) {
            beforeContainerRegForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reset_LoginDetails".equals(c.getName())) {
            beforeContainerResetLoginDetails(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerPasswordRecovery(Container c) {
    }


    protected void beforeContainerEditLogin(Container c) {
    }


    protected void beforeContainerVerifyToken(Container c) {
    }


    protected void beforeContainerAlertForm(Container c) {
    }


    protected void beforeContainerAboutForm(Container c) {
    }


    protected void beforeContainerDeviceForm(Container c) {
    }


    protected void beforeContainerEditContactForm(Container c) {
    }


    protected void beforeContainerEmergencyContactsInformForm(Container c) {
    }


    protected void beforeContainerSplashForm(Container c) {
    }


    protected void beforeContainerEditDeviceForm(Container c) {
    }


    protected void beforeContainerEditRegInformForm(Container c) {
    }


    protected void beforeContainerLoginForm(Container c) {
    }


    protected void beforeContainerContactsForm(Container c) {
    }


    protected void beforeContainerRegForm(Container c) {
    }


    protected void beforeContainerResetLoginDetails(Container c) {
    }

    protected void postShow(Form f) {
        if("Password_Recovery".equals(f.getName())) {
            postPasswordRecovery(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Login".equals(f.getName())) {
            postEditLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VerifyToken".equals(f.getName())) {
            postVerifyToken(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Alert_Form".equals(f.getName())) {
            postAlertForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("About_Form".equals(f.getName())) {
            postAboutForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Device_Form".equals(f.getName())) {
            postDeviceForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Contact_Form".equals(f.getName())) {
            postEditContactForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Emergency_Contacts_Inform_Form".equals(f.getName())) {
            postEmergencyContactsInformForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash_Form".equals(f.getName())) {
            postSplashForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Device_Form".equals(f.getName())) {
            postEditDeviceForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Reg_Inform_Form".equals(f.getName())) {
            postEditRegInformForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login_Form".equals(f.getName())) {
            postLoginForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts_Form".equals(f.getName())) {
            postContactsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reg_Form".equals(f.getName())) {
            postRegForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reset_LoginDetails".equals(f.getName())) {
            postResetLoginDetails(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postPasswordRecovery(Form f) {
    }


    protected void postEditLogin(Form f) {
    }


    protected void postVerifyToken(Form f) {
    }


    protected void postAlertForm(Form f) {
    }


    protected void postAboutForm(Form f) {
    }


    protected void postDeviceForm(Form f) {
    }


    protected void postEditContactForm(Form f) {
    }


    protected void postEmergencyContactsInformForm(Form f) {
    }


    protected void postSplashForm(Form f) {
    }


    protected void postEditDeviceForm(Form f) {
    }


    protected void postEditRegInformForm(Form f) {
    }


    protected void postLoginForm(Form f) {
    }


    protected void postContactsForm(Form f) {
    }


    protected void postRegForm(Form f) {
    }


    protected void postResetLoginDetails(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("Password_Recovery".equals(c.getName())) {
            postContainerPasswordRecovery(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Login".equals(c.getName())) {
            postContainerEditLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VerifyToken".equals(c.getName())) {
            postContainerVerifyToken(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Alert_Form".equals(c.getName())) {
            postContainerAlertForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("About_Form".equals(c.getName())) {
            postContainerAboutForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Device_Form".equals(c.getName())) {
            postContainerDeviceForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Contact_Form".equals(c.getName())) {
            postContainerEditContactForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Emergency_Contacts_Inform_Form".equals(c.getName())) {
            postContainerEmergencyContactsInformForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash_Form".equals(c.getName())) {
            postContainerSplashForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Device_Form".equals(c.getName())) {
            postContainerEditDeviceForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Reg_Inform_Form".equals(c.getName())) {
            postContainerEditRegInformForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login_Form".equals(c.getName())) {
            postContainerLoginForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts_Form".equals(c.getName())) {
            postContainerContactsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reg_Form".equals(c.getName())) {
            postContainerRegForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reset_LoginDetails".equals(c.getName())) {
            postContainerResetLoginDetails(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerPasswordRecovery(Container c) {
    }


    protected void postContainerEditLogin(Container c) {
    }


    protected void postContainerVerifyToken(Container c) {
    }


    protected void postContainerAlertForm(Container c) {
    }


    protected void postContainerAboutForm(Container c) {
    }


    protected void postContainerDeviceForm(Container c) {
    }


    protected void postContainerEditContactForm(Container c) {
    }


    protected void postContainerEmergencyContactsInformForm(Container c) {
    }


    protected void postContainerSplashForm(Container c) {
    }


    protected void postContainerEditDeviceForm(Container c) {
    }


    protected void postContainerEditRegInformForm(Container c) {
    }


    protected void postContainerLoginForm(Container c) {
    }


    protected void postContainerContactsForm(Container c) {
    }


    protected void postContainerRegForm(Container c) {
    }


    protected void postContainerResetLoginDetails(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("Password_Recovery".equals(rootName)) {
            onCreatePasswordRecovery();
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Login".equals(rootName)) {
            onCreateEditLogin();
            aboutToShowThisContainer = null;
            return;
        }

        if("VerifyToken".equals(rootName)) {
            onCreateVerifyToken();
            aboutToShowThisContainer = null;
            return;
        }

        if("Alert_Form".equals(rootName)) {
            onCreateAlertForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("About_Form".equals(rootName)) {
            onCreateAboutForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Device_Form".equals(rootName)) {
            onCreateDeviceForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Contact_Form".equals(rootName)) {
            onCreateEditContactForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Emergency_Contacts_Inform_Form".equals(rootName)) {
            onCreateEmergencyContactsInformForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash_Form".equals(rootName)) {
            onCreateSplashForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Device_Form".equals(rootName)) {
            onCreateEditDeviceForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Reg_Inform_Form".equals(rootName)) {
            onCreateEditRegInformForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Login_Form".equals(rootName)) {
            onCreateLoginForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts_Form".equals(rootName)) {
            onCreateContactsForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Reg_Form".equals(rootName)) {
            onCreateRegForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Reset_LoginDetails".equals(rootName)) {
            onCreateResetLoginDetails();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreatePasswordRecovery() {
    }


    protected void onCreateEditLogin() {
    }


    protected void onCreateVerifyToken() {
    }


    protected void onCreateAlertForm() {
    }


    protected void onCreateAboutForm() {
    }


    protected void onCreateDeviceForm() {
    }


    protected void onCreateEditContactForm() {
    }


    protected void onCreateEmergencyContactsInformForm() {
    }


    protected void onCreateSplashForm() {
    }


    protected void onCreateEditDeviceForm() {
    }


    protected void onCreateEditRegInformForm() {
    }


    protected void onCreateLoginForm() {
    }


    protected void onCreateContactsForm() {
    }


    protected void onCreateRegForm() {
    }


    protected void onCreateResetLoginDetails() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("Password_Recovery".equals(f.getName())) {
            getStatePasswordRecovery(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Edit_Login".equals(f.getName())) {
            getStateEditLogin(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("VerifyToken".equals(f.getName())) {
            getStateVerifyToken(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Alert_Form".equals(f.getName())) {
            getStateAlertForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("About_Form".equals(f.getName())) {
            getStateAboutForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Device_Form".equals(f.getName())) {
            getStateDeviceForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Edit_Contact_Form".equals(f.getName())) {
            getStateEditContactForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Emergency_Contacts_Inform_Form".equals(f.getName())) {
            getStateEmergencyContactsInformForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Splash_Form".equals(f.getName())) {
            getStateSplashForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Edit_Device_Form".equals(f.getName())) {
            getStateEditDeviceForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Edit_Reg_Inform_Form".equals(f.getName())) {
            getStateEditRegInformForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Login_Form".equals(f.getName())) {
            getStateLoginForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Contacts_Form".equals(f.getName())) {
            getStateContactsForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Reg_Form".equals(f.getName())) {
            getStateRegForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Reset_LoginDetails".equals(f.getName())) {
            getStateResetLoginDetails(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStatePasswordRecovery(Form f, Hashtable h) {
    }


    protected void getStateEditLogin(Form f, Hashtable h) {
    }


    protected void getStateVerifyToken(Form f, Hashtable h) {
    }


    protected void getStateAlertForm(Form f, Hashtable h) {
    }


    protected void getStateAboutForm(Form f, Hashtable h) {
    }


    protected void getStateDeviceForm(Form f, Hashtable h) {
    }


    protected void getStateEditContactForm(Form f, Hashtable h) {
    }


    protected void getStateEmergencyContactsInformForm(Form f, Hashtable h) {
    }


    protected void getStateSplashForm(Form f, Hashtable h) {
    }


    protected void getStateEditDeviceForm(Form f, Hashtable h) {
    }


    protected void getStateEditRegInformForm(Form f, Hashtable h) {
    }


    protected void getStateLoginForm(Form f, Hashtable h) {
    }


    protected void getStateContactsForm(Form f, Hashtable h) {
    }


    protected void getStateRegForm(Form f, Hashtable h) {
    }


    protected void getStateResetLoginDetails(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("Password_Recovery".equals(f.getName())) {
            setStatePasswordRecovery(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Login".equals(f.getName())) {
            setStateEditLogin(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("VerifyToken".equals(f.getName())) {
            setStateVerifyToken(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Alert_Form".equals(f.getName())) {
            setStateAlertForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("About_Form".equals(f.getName())) {
            setStateAboutForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Device_Form".equals(f.getName())) {
            setStateDeviceForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Contact_Form".equals(f.getName())) {
            setStateEditContactForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Emergency_Contacts_Inform_Form".equals(f.getName())) {
            setStateEmergencyContactsInformForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash_Form".equals(f.getName())) {
            setStateSplashForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Device_Form".equals(f.getName())) {
            setStateEditDeviceForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Edit_Reg_Inform_Form".equals(f.getName())) {
            setStateEditRegInformForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login_Form".equals(f.getName())) {
            setStateLoginForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts_Form".equals(f.getName())) {
            setStateContactsForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reg_Form".equals(f.getName())) {
            setStateRegForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Reset_LoginDetails".equals(f.getName())) {
            setStateResetLoginDetails(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStatePasswordRecovery(Form f, Hashtable state) {
    }


    protected void setStateEditLogin(Form f, Hashtable state) {
    }


    protected void setStateVerifyToken(Form f, Hashtable state) {
    }


    protected void setStateAlertForm(Form f, Hashtable state) {
    }


    protected void setStateAboutForm(Form f, Hashtable state) {
    }


    protected void setStateDeviceForm(Form f, Hashtable state) {
    }


    protected void setStateEditContactForm(Form f, Hashtable state) {
    }


    protected void setStateEmergencyContactsInformForm(Form f, Hashtable state) {
    }


    protected void setStateSplashForm(Form f, Hashtable state) {
    }


    protected void setStateEditDeviceForm(Form f, Hashtable state) {
    }


    protected void setStateEditRegInformForm(Form f, Hashtable state) {
    }


    protected void setStateLoginForm(Form f, Hashtable state) {
    }


    protected void setStateContactsForm(Form f, Hashtable state) {
    }


    protected void setStateRegForm(Form f, Hashtable state) {
    }


    protected void setStateResetLoginDetails(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("RelaCB".equals(listName)) {
            return initListModelRelaCB(cmp);
        }
        if("DeviceOnOffSwitch".equals(listName)) {
            return initListModelDeviceOnOffSwitch(cmp);
        }
        if("LgaCB".equals(listName)) {
            return initListModelLgaCB(cmp);
        }
        if("StaCB".equals(listName)) {
            return initListModelStaCB(cmp);
        }
        if("ContactsOnOffSwitch".equals(listName)) {
            return initListModelContactsOnOffSwitch(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelRelaCB(List cmp) {
        return false;
    }

    protected boolean initListModelDeviceOnOffSwitch(List cmp) {
        return false;
    }

    protected boolean initListModelLgaCB(List cmp) {
        return false;
    }

    protected boolean initListModelStaCB(List cmp) {
        return false;
    }

    protected boolean initListModelContactsOnOffSwitch(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("Password_Recovery")) {
            if("PassRecoverUserTF".equals(c.getName())) {
                onPasswordRecovery_PassRecoverUserTFAction(c, event);
                return;
            }
            if("TokenLoginBut".equals(c.getName())) {
                onPasswordRecovery_TokenLoginButAction(c, event);
                return;
            }
            if("VerifyButt".equals(c.getName())) {
                onPasswordRecovery_VerifyButtAction(c, event);
                return;
            }
            if("PSHomeBut".equals(c.getName())) {
                onPasswordRecovery_PSHomeButAction(c, event);
                return;
            }
            if("PSAboutBut".equals(c.getName())) {
                onPasswordRecovery_PSAboutButAction(c, event);
                return;
            }
            if("PSEmeBut".equals(c.getName())) {
                onPasswordRecovery_PSEmeButAction(c, event);
                return;
            }
            if("PSSetBut".equals(c.getName())) {
                onPasswordRecovery_PSSetButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Edit_Login")) {
            if("OldPassTF".equals(c.getName())) {
                onEditLogin_OldPassTFAction(c, event);
                return;
            }
            if("PassTF".equals(c.getName())) {
                onEditLogin_PassTFAction(c, event);
                return;
            }
            if("VpassTF".equals(c.getName())) {
                onEditLogin_VpassTFAction(c, event);
                return;
            }
            if("EditLoginBut".equals(c.getName())) {
                onEditLogin_EditLoginButAction(c, event);
                return;
            }
            if("EditLoginHomeBut".equals(c.getName())) {
                onEditLogin_EditLoginHomeButAction(c, event);
                return;
            }
            if("EditLoginAboutBut".equals(c.getName())) {
                onEditLogin_EditLoginAboutButAction(c, event);
                return;
            }
            if("EditLoginEmeBut".equals(c.getName())) {
                onEditLogin_EditLoginEmeButAction(c, event);
                return;
            }
            if("EditLoginSetBut".equals(c.getName())) {
                onEditLogin_EditLoginSetButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("VerifyToken")) {
            if("TokenTF".equals(c.getName())) {
                onVerifyToken_TokenTFAction(c, event);
                return;
            }
            if("PSLoginBut".equals(c.getName())) {
                onVerifyToken_PSLoginButAction(c, event);
                return;
            }
            if("PSHomeBut".equals(c.getName())) {
                onVerifyToken_PSHomeButAction(c, event);
                return;
            }
            if("PSAboutBut".equals(c.getName())) {
                onVerifyToken_PSAboutButAction(c, event);
                return;
            }
            if("PSEmeBut".equals(c.getName())) {
                onVerifyToken_PSEmeButAction(c, event);
                return;
            }
            if("PSSetBut".equals(c.getName())) {
                onVerifyToken_PSSetButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("About_Form")) {
            if("AboutHomeBut".equals(c.getName())) {
                onAboutForm_AboutHomeButAction(c, event);
                return;
            }
            if("AboutBut".equals(c.getName())) {
                onAboutForm_AboutButAction(c, event);
                return;
            }
            if("AboutContactsBut".equals(c.getName())) {
                onAboutForm_AboutContactsButAction(c, event);
                return;
            }
            if("AboutSettingBut".equals(c.getName())) {
                onAboutForm_AboutSettingButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Device_Form")) {
            if("DevNameTF".equals(c.getName())) {
                onDeviceForm_DevNameTFAction(c, event);
                return;
            }
            if("DesTA".equals(c.getName())) {
                onDeviceForm_DesTAAction(c, event);
                return;
            }
            if("AddTA2".equals(c.getName())) {
                onDeviceForm_AddTA2Action(c, event);
                return;
            }
            if("BuildTypeTF".equals(c.getName())) {
                onDeviceForm_BuildTypeTFAction(c, event);
                return;
            }
            if("LocTF".equals(c.getName())) {
                onDeviceForm_LocTFAction(c, event);
                return;
            }
            if("StaCB".equals(c.getName())) {
                onDeviceForm_StaCBAction(c, event);
                return;
            }
            if("LgaCB".equals(c.getName())) {
                onDeviceForm_LgaCBAction(c, event);
                return;
            }
            if("DeviceOnOffSwitch".equals(c.getName())) {
                onDeviceForm_DeviceOnOffSwitchAction(c, event);
                return;
            }
            if("DevBut".equals(c.getName())) {
                onDeviceForm_DevButAction(c, event);
                return;
            }
            if("PSHomeBut".equals(c.getName())) {
                onDeviceForm_PSHomeButAction(c, event);
                return;
            }
            if("PSAboutBut".equals(c.getName())) {
                onDeviceForm_PSAboutButAction(c, event);
                return;
            }
            if("PSEmeBut".equals(c.getName())) {
                onDeviceForm_PSEmeButAction(c, event);
                return;
            }
            if("PSSetBut".equals(c.getName())) {
                onDeviceForm_PSSetButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Edit_Contact_Form")) {
            if("EditConHomeBut".equals(c.getName())) {
                onEditContactForm_EditConHomeButAction(c, event);
                return;
            }
            if("EditConAboutBut".equals(c.getName())) {
                onEditContactForm_EditConAboutButAction(c, event);
                return;
            }
            if("EditConContactBut".equals(c.getName())) {
                onEditContactForm_EditConContactButAction(c, event);
                return;
            }
            if("EditConSettingsBut".equals(c.getName())) {
                onEditContactForm_EditConSettingsButAction(c, event);
                return;
            }
            if("NameTF".equals(c.getName())) {
                onEditContactForm_NameTFAction(c, event);
                return;
            }
            if("MobTF".equals(c.getName())) {
                onEditContactForm_MobTFAction(c, event);
                return;
            }
            if("EmailTF".equals(c.getName())) {
                onEditContactForm_EmailTFAction(c, event);
                return;
            }
            if("AddTA".equals(c.getName())) {
                onEditContactForm_AddTAAction(c, event);
                return;
            }
            if("RelaTF".equals(c.getName())) {
                onEditContactForm_RelaTFAction(c, event);
                return;
            }
            if("PreBut".equals(c.getName())) {
                onEditContactForm_PreButAction(c, event);
                return;
            }
            if("NextBut".equals(c.getName())) {
                onEditContactForm_NextButAction(c, event);
                return;
            }
            if("EdittContactButton".equals(c.getName())) {
                onEditContactForm_EdittContactButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Emergency_Contacts_Inform_Form")) {
            if("EmeHome".equals(c.getName())) {
                onEmergencyContactsInformForm_EmeHomeAction(c, event);
                return;
            }
            if("EmeAboutBut".equals(c.getName())) {
                onEmergencyContactsInformForm_EmeAboutButAction(c, event);
                return;
            }
            if("EmeContactBut".equals(c.getName())) {
                onEmergencyContactsInformForm_EmeContactButAction(c, event);
                return;
            }
            if("EmeSettingBut".equals(c.getName())) {
                onEmergencyContactsInformForm_EmeSettingButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Splash_Form")) {
            if("HomeBut".equals(c.getName())) {
                onSplashForm_HomeButAction(c, event);
                return;
            }
            if("AboutBut".equals(c.getName())) {
                onSplashForm_AboutButAction(c, event);
                return;
            }
            if("EmeBut".equals(c.getName())) {
                onSplashForm_EmeButAction(c, event);
                return;
            }
            if("SetBut".equals(c.getName())) {
                onSplashForm_SetButAction(c, event);
                return;
            }
            if("HomeRegBut".equals(c.getName())) {
                onSplashForm_HomeRegButAction(c, event);
                return;
            }
            if("HomeLoginBut".equals(c.getName())) {
                onSplashForm_HomeLoginButAction(c, event);
                return;
            }
            if("HomeFutBut".equals(c.getName())) {
                onSplashForm_HomeFutButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Edit_Device_Form")) {
            if("DevNameTF".equals(c.getName())) {
                onEditDeviceForm_DevNameTFAction(c, event);
                return;
            }
            if("DesTA".equals(c.getName())) {
                onEditDeviceForm_DesTAAction(c, event);
                return;
            }
            if("AddTA2".equals(c.getName())) {
                onEditDeviceForm_AddTA2Action(c, event);
                return;
            }
            if("BuildTypeTF".equals(c.getName())) {
                onEditDeviceForm_BuildTypeTFAction(c, event);
                return;
            }
            if("LocTF".equals(c.getName())) {
                onEditDeviceForm_LocTFAction(c, event);
                return;
            }
            if("StateTF".equals(c.getName())) {
                onEditDeviceForm_StateTFAction(c, event);
                return;
            }
            if("LgaTF".equals(c.getName())) {
                onEditDeviceForm_LgaTFAction(c, event);
                return;
            }
            if("PreBut".equals(c.getName())) {
                onEditDeviceForm_PreButAction(c, event);
                return;
            }
            if("NextBut".equals(c.getName())) {
                onEditDeviceForm_NextButAction(c, event);
                return;
            }
            if("SaveButt".equals(c.getName())) {
                onEditDeviceForm_SaveButtAction(c, event);
                return;
            }
            if("PSHomeBut".equals(c.getName())) {
                onEditDeviceForm_PSHomeButAction(c, event);
                return;
            }
            if("PSAboutBut".equals(c.getName())) {
                onEditDeviceForm_PSAboutButAction(c, event);
                return;
            }
            if("PSEmeBut".equals(c.getName())) {
                onEditDeviceForm_PSEmeButAction(c, event);
                return;
            }
            if("PSSetBut".equals(c.getName())) {
                onEditDeviceForm_PSSetButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Edit_Reg_Inform_Form")) {
            if("NameTF".equals(c.getName())) {
                onEditRegInformForm_NameTFAction(c, event);
                return;
            }
            if("MobTF".equals(c.getName())) {
                onEditRegInformForm_MobTFAction(c, event);
                return;
            }
            if("EmailTF".equals(c.getName())) {
                onEditRegInformForm_EmailTFAction(c, event);
                return;
            }
            if("AddTA".equals(c.getName())) {
                onEditRegInformForm_AddTAAction(c, event);
                return;
            }
            if("StateTF".equals(c.getName())) {
                onEditRegInformForm_StateTFAction(c, event);
                return;
            }
            if("LgaTF".equals(c.getName())) {
                onEditRegInformForm_LgaTFAction(c, event);
                return;
            }
            if("EditRegBut".equals(c.getName())) {
                onEditRegInformForm_EditRegButAction(c, event);
                return;
            }
            if("EditRegHomeBut".equals(c.getName())) {
                onEditRegInformForm_EditRegHomeButAction(c, event);
                return;
            }
            if("EditRegAboutBut".equals(c.getName())) {
                onEditRegInformForm_EditRegAboutButAction(c, event);
                return;
            }
            if("EditRegContBut".equals(c.getName())) {
                onEditRegInformForm_EditRegContButAction(c, event);
                return;
            }
            if("EditRegSetBut".equals(c.getName())) {
                onEditRegInformForm_EditRegSetButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Login_Form")) {
            if("HomeBut".equals(c.getName())) {
                onLoginForm_HomeButAction(c, event);
                return;
            }
            if("AboutBut".equals(c.getName())) {
                onLoginForm_AboutButAction(c, event);
                return;
            }
            if("EmeBut".equals(c.getName())) {
                onLoginForm_EmeButAction(c, event);
                return;
            }
            if("SetBut".equals(c.getName())) {
                onLoginForm_SetButAction(c, event);
                return;
            }
            if("UserTF".equals(c.getName())) {
                onLoginForm_UserTFAction(c, event);
                return;
            }
            if("PassTF".equals(c.getName())) {
                onLoginForm_PassTFAction(c, event);
                return;
            }
            if("LoginBut".equals(c.getName())) {
                onLoginForm_LoginButAction(c, event);
                return;
            }
            if("SignedUpBut".equals(c.getName())) {
                onLoginForm_SignedUpButAction(c, event);
                return;
            }
            if("RecoverBut".equals(c.getName())) {
                onLoginForm_RecoverButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Contacts_Form")) {
            if("NameTF".equals(c.getName())) {
                onContactsForm_NameTFAction(c, event);
                return;
            }
            if("OthernameTF".equals(c.getName())) {
                onContactsForm_OthernameTFAction(c, event);
                return;
            }
            if("MobTF".equals(c.getName())) {
                onContactsForm_MobTFAction(c, event);
                return;
            }
            if("EmailTF".equals(c.getName())) {
                onContactsForm_EmailTFAction(c, event);
                return;
            }
            if("AddTA".equals(c.getName())) {
                onContactsForm_AddTAAction(c, event);
                return;
            }
            if("RelaCB".equals(c.getName())) {
                onContactsForm_RelaCBAction(c, event);
                return;
            }
            if("ContactsOnOffSwitch".equals(c.getName())) {
                onContactsForm_ContactsOnOffSwitchAction(c, event);
                return;
            }
            if("ContBut".equals(c.getName())) {
                onContactsForm_ContButAction(c, event);
                return;
            }
            if("ConHomeBut".equals(c.getName())) {
                onContactsForm_ConHomeButAction(c, event);
                return;
            }
            if("ConAboutBut".equals(c.getName())) {
                onContactsForm_ConAboutButAction(c, event);
                return;
            }
            if("ConContactBut".equals(c.getName())) {
                onContactsForm_ConContactButAction(c, event);
                return;
            }
            if("ConSettingsBut".equals(c.getName())) {
                onContactsForm_ConSettingsButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Reg_Form")) {
            if("NameTF".equals(c.getName())) {
                onRegForm_NameTFAction(c, event);
                return;
            }
            if("FirstnameTF".equals(c.getName())) {
                onRegForm_FirstnameTFAction(c, event);
                return;
            }
            if("OthernameTF".equals(c.getName())) {
                onRegForm_OthernameTFAction(c, event);
                return;
            }
            if("MobTF".equals(c.getName())) {
                onRegForm_MobTFAction(c, event);
                return;
            }
            if("EmailTF".equals(c.getName())) {
                onRegForm_EmailTFAction(c, event);
                return;
            }
            if("AddTA".equals(c.getName())) {
                onRegForm_AddTAAction(c, event);
                return;
            }
            if("StaCB".equals(c.getName())) {
                onRegForm_StaCBAction(c, event);
                return;
            }
            if("LgaCB".equals(c.getName())) {
                onRegForm_LgaCBAction(c, event);
                return;
            }
            if("PassTF".equals(c.getName())) {
                onRegForm_PassTFAction(c, event);
                return;
            }
            if("VpassTF".equals(c.getName())) {
                onRegForm_VpassTFAction(c, event);
                return;
            }
            if("RegBut".equals(c.getName())) {
                onRegForm_RegButAction(c, event);
                return;
            }
            if("NotLogginedBut".equals(c.getName())) {
                onRegForm_NotLogginedButAction(c, event);
                return;
            }
            if("RegHomeBut".equals(c.getName())) {
                onRegForm_RegHomeButAction(c, event);
                return;
            }
            if("RegAboutBut".equals(c.getName())) {
                onRegForm_RegAboutButAction(c, event);
                return;
            }
            if("RegContBut".equals(c.getName())) {
                onRegForm_RegContButAction(c, event);
                return;
            }
            if("RegSetBut".equals(c.getName())) {
                onRegForm_RegSetButAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Reset_LoginDetails")) {
            if("EditLoginHomeBut".equals(c.getName())) {
                onResetLoginDetails_EditLoginHomeButAction(c, event);
                return;
            }
            if("EditLoginAboutBut".equals(c.getName())) {
                onResetLoginDetails_EditLoginAboutButAction(c, event);
                return;
            }
            if("EditLoginEmeBut".equals(c.getName())) {
                onResetLoginDetails_EditLoginEmeButAction(c, event);
                return;
            }
            if("EditLoginSetBut".equals(c.getName())) {
                onResetLoginDetails_EditLoginSetButAction(c, event);
                return;
            }
            if("ResetEmailTF".equals(c.getName())) {
                onResetLoginDetails_ResetEmailTFAction(c, event);
                return;
            }
            if("PassTF".equals(c.getName())) {
                onResetLoginDetails_PassTFAction(c, event);
                return;
            }
            if("VpassTF".equals(c.getName())) {
                onResetLoginDetails_VpassTFAction(c, event);
                return;
            }
            if("EditLoginBut".equals(c.getName())) {
                onResetLoginDetails_EditLoginButAction(c, event);
                return;
            }
        }
    }

      protected void onPasswordRecovery_PassRecoverUserTFAction(Component c, ActionEvent event) {
      }

      protected void onPasswordRecovery_TokenLoginButAction(Component c, ActionEvent event) {
      }

      protected void onPasswordRecovery_VerifyButtAction(Component c, ActionEvent event) {
      }

      protected void onPasswordRecovery_PSHomeButAction(Component c, ActionEvent event) {
      }

      protected void onPasswordRecovery_PSAboutButAction(Component c, ActionEvent event) {
      }

      protected void onPasswordRecovery_PSEmeButAction(Component c, ActionEvent event) {
      }

      protected void onPasswordRecovery_PSSetButAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_OldPassTFAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_PassTFAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_VpassTFAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_EditLoginButAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_EditLoginHomeButAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_EditLoginAboutButAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_EditLoginEmeButAction(Component c, ActionEvent event) {
      }

      protected void onEditLogin_EditLoginSetButAction(Component c, ActionEvent event) {
      }

      protected void onVerifyToken_TokenTFAction(Component c, ActionEvent event) {
      }

      protected void onVerifyToken_PSLoginButAction(Component c, ActionEvent event) {
      }

      protected void onVerifyToken_PSHomeButAction(Component c, ActionEvent event) {
      }

      protected void onVerifyToken_PSAboutButAction(Component c, ActionEvent event) {
      }

      protected void onVerifyToken_PSEmeButAction(Component c, ActionEvent event) {
      }

      protected void onVerifyToken_PSSetButAction(Component c, ActionEvent event) {
      }

      protected void onAboutForm_AboutHomeButAction(Component c, ActionEvent event) {
      }

      protected void onAboutForm_AboutButAction(Component c, ActionEvent event) {
      }

      protected void onAboutForm_AboutContactsButAction(Component c, ActionEvent event) {
      }

      protected void onAboutForm_AboutSettingButAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_DevNameTFAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_DesTAAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_AddTA2Action(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_BuildTypeTFAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_LocTFAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_StaCBAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_LgaCBAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_DeviceOnOffSwitchAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_DevButAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_PSHomeButAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_PSAboutButAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_PSEmeButAction(Component c, ActionEvent event) {
      }

      protected void onDeviceForm_PSSetButAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_EditConHomeButAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_EditConAboutButAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_EditConContactButAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_EditConSettingsButAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_NameTFAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_MobTFAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_EmailTFAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_AddTAAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_RelaTFAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_PreButAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_NextButAction(Component c, ActionEvent event) {
      }

      protected void onEditContactForm_EdittContactButtonAction(Component c, ActionEvent event) {
      }

      protected void onEmergencyContactsInformForm_EmeHomeAction(Component c, ActionEvent event) {
      }

      protected void onEmergencyContactsInformForm_EmeAboutButAction(Component c, ActionEvent event) {
      }

      protected void onEmergencyContactsInformForm_EmeContactButAction(Component c, ActionEvent event) {
      }

      protected void onEmergencyContactsInformForm_EmeSettingButAction(Component c, ActionEvent event) {
      }

      protected void onSplashForm_HomeButAction(Component c, ActionEvent event) {
      }

      protected void onSplashForm_AboutButAction(Component c, ActionEvent event) {
      }

      protected void onSplashForm_EmeButAction(Component c, ActionEvent event) {
      }

      protected void onSplashForm_SetButAction(Component c, ActionEvent event) {
      }

      protected void onSplashForm_HomeRegButAction(Component c, ActionEvent event) {
      }

      protected void onSplashForm_HomeLoginButAction(Component c, ActionEvent event) {
      }

      protected void onSplashForm_HomeFutButAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_DevNameTFAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_DesTAAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_AddTA2Action(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_BuildTypeTFAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_LocTFAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_StateTFAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_LgaTFAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_PreButAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_NextButAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_SaveButtAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_PSHomeButAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_PSAboutButAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_PSEmeButAction(Component c, ActionEvent event) {
      }

      protected void onEditDeviceForm_PSSetButAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_NameTFAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_MobTFAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_EmailTFAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_AddTAAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_StateTFAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_LgaTFAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_EditRegButAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_EditRegHomeButAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_EditRegAboutButAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_EditRegContButAction(Component c, ActionEvent event) {
      }

      protected void onEditRegInformForm_EditRegSetButAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_HomeButAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_AboutButAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_EmeButAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_SetButAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_UserTFAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_PassTFAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_LoginButAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_SignedUpButAction(Component c, ActionEvent event) {
      }

      protected void onLoginForm_RecoverButAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_NameTFAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_OthernameTFAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_MobTFAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_EmailTFAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_AddTAAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_RelaCBAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_ContactsOnOffSwitchAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_ContButAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_ConHomeButAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_ConAboutButAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_ConContactButAction(Component c, ActionEvent event) {
      }

      protected void onContactsForm_ConSettingsButAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_NameTFAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_FirstnameTFAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_OthernameTFAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_MobTFAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_EmailTFAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_AddTAAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_StaCBAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_LgaCBAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_PassTFAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_VpassTFAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_RegButAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_NotLogginedButAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_RegHomeButAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_RegAboutButAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_RegContButAction(Component c, ActionEvent event) {
      }

      protected void onRegForm_RegSetButAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_EditLoginHomeButAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_EditLoginAboutButAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_EditLoginEmeButAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_EditLoginSetButAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_ResetEmailTFAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_PassTFAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_VpassTFAction(Component c, ActionEvent event) {
      }

      protected void onResetLoginDetails_EditLoginButAction(Component c, ActionEvent event) {
      }

}
