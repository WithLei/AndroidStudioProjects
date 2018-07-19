package com.onyx.android.note.note.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.onyx.android.note.cloudnote.bean.NoteArgs;
import com.onyx.android.note.cloudnote.service.ExportNotePdfService;
import com.onyx.android.note.common.base.BaseAction;
import com.onyx.android.note.common.base.BaseFragment;
import com.onyx.android.note.common.manager.ResManager;
import com.onyx.android.note.common.rxbinding.RxView;
import com.onyx.android.note.common.utils.DurationGuard;
import com.onyx.android.note.common.utils.NoteUtils;
import com.onyx.android.note.databinding.FragmentScribbleBinding;
import com.onyx.android.note.databinding.LayoutNoteFuncMenuBinding;
import com.onyx.android.note.databinding.LayoutNoteToolMenuBinding;
import com.onyx.android.note.library.LibraryDataBundle;
import com.onyx.android.note.library.event.RefreshLibrariesEvent;
import com.onyx.android.note.note.NoteDataBundle;
import com.onyx.android.note.note.PenEventHandler;
import com.onyx.android.note.note.action.doc.CreateDocumentAction;
import com.onyx.android.note.note.action.doc.OpenDocumentAction;
import com.onyx.android.note.note.action.doc.SaveDocumentAction;
import com.onyx.android.note.note.action.shape.RefreshDrawScreenAction;
import com.onyx.android.note.note.action.ui.CapacitanceTpEnableAction;
import com.onyx.android.note.note.action.ui.TouchAreaIgnoreAction;
import com.onyx.android.note.note.event.OpenDocumentRequestEvent;
import com.onyx.android.note.note.event.QuitScribbleEvent;
import com.onyx.android.note.note.event.shape.BuildSpanTextShapeEvent;
import com.onyx.android.note.note.event.ui.ResizeMenuEvent;
import com.onyx.android.note.note.event.ui.ResizeViewEvent;
import com.onyx.android.note.note.event.ui.SpanViewEnableEvent;
import com.onyx.android.note.note.event.ui.SpanViewEvent;
import com.onyx.android.note.note.handler.HandlerManager;
import com.onyx.android.note.note.menu.model.FuncBarMenuModel;
import com.onyx.android.note.note.menu.model.ScribbleMenuModel;
import com.onyx.android.note.note.receiver.DeviceReceiver;
import com.onyx.android.note.widget.AlertDialog;
import com.onyx.android.sdk.note.NoteManager;
import com.onyx.android.sdk.note.event.NoteInfoEvent;
import com.onyx.android.sdk.note.widget.LinedEditText;
import com.onyx.android.sdk.scribble.data.DocumentOptionArgs;
import com.onyx.android.sdk.scribble.data.NoteDrawingArgs;
import com.onyx.android.sdk.scribble.data.NoteModel;
import com.onyx.android.sdk.scribble.shape.RenderContext;
import com.onyx.android.sdk.utils.StringUtils;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ScribbleFragment
        extends BaseFragment
{
    private static final String b = "ScribbleFragment";
    private FragmentScribbleBinding c;
    private SurfaceHolder.Callback d;
    private ScribbleMenuModel e;
    private DeviceReceiver f = new DeviceReceiver();
    private String g;
    private String h;
    private DurationGuard i = new DurationGuard(500L);

    private void a(float paramFloat)
    {
        b(paramFloat);
        f();
        e();
    }

    private void a(NoteDrawingArgs paramNoteDrawingArgs)
    {
        if (paramNoteDrawingArgs.getStrokeColor() == -1) {
            AlertDialog.getConfirmDialog(getActivity(), ResManager.getString(2131493011), false, null, null).setControlPen(true).show();
        }
    }

    private void a(NoteModel paramNoteModel)
    {
        this.g = paramNoteModel.getUniqueId();
        this.h = paramNoteModel.getParentUniqueId();
        r().setNoteTitle(NoteUtils.getNoteTitle(paramNoteModel));
        c();
    }

    private void a(String paramString)
    {
        s().post(new BuildSpanTextShapeEvent(paramString));
    }

    private void a(String paramString1, String paramString2)
    {
        r().getHandlerManager().reset();
        new OpenDocumentAction(s()).setDocumentUniqueId(paramString1).setParentUniqueId(paramString2).setOptionArgs(DocumentOptionArgs.create()).enableProgress(getActivity(), 2131492977).execute(new ScribbleFragment.8(this));
    }

    private void b()
    {
        m();
        j();
        c();
        l();
    }

    private void b(float paramFloat)
    {
        this.c.spanTextView.setText("");
        this.c.spanTextView.setLineSpacing(0.0F, 1.5F * paramFloat);
    }

    private void b(String paramString)
    {
        paramString = NoteArgs.create(this.g, this.h, paramString).setExportPdfFileName(NoteUtils.getExportPdfTitleWithTime(paramString));
        ExportNotePdfService.startService(getActivity(), paramString);
    }

    private void c()
    {
        String str = r().getNoteTitle();
        if (StringUtils.isNotBlank(str)) {
            getScribbleMenuModel().funcBarMenuModel.setNoteName(str);
        }
    }

    private void d()
    {
        i();
        float f1 = h();
        a(f1);
        Rect localRect = new Rect();
        this.c.surfaceView.getLocalVisibleRect(localRect);
        r().post(new ResizeViewEvent(f1, localRect));
    }

    private void e()
    {
        float f1 = 1.0F / r().getDrawingArgs().getScale();
        RenderContext localRenderContext = s().getRenderContext();
        Matrix localMatrix = new Matrix();
        localMatrix.setScale(f1, f1);
        localRenderContext.setMatrix(localMatrix);
    }

    private void f()
    {
        r().getDrawingArgs().setScale(Math.min(1.0F / this.c.surfaceView.getWidth(), 1.0F / this.c.surfaceView.getHeight()));
    }

    private boolean g()
    {
        return (this.c.funcMenu.menuLayout.getVisibility() != 0) && (this.c.toolMenu.menuLayout.getVisibility() != 0);
    }

    private float h()
    {
        if (g()) {
            return 1.0F;
        }
        int j = this.c.content.getWidth();
        return this.c.surfaceView.getWidth() / j;
    }

    private void i()
    {
        Rect localRect = new Rect();
        this.c.surfaceView.getLocalVisibleRect(localRect);
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(localRect);
        s().setDrawLimitRect(localArrayList);
    }

    private void j()
    {
        this.c.spanTextView.setOnKeyListener(new ScribbleFragment.3(this));
        this.c.spanTextView.setInputConnectionListener(new ScribbleFragment.4(this));
        this.c.spanTextView.setOnKeyPreImeListener(new ScribbleFragment.5(this));
    }

    private void k()
    {
        this.f.unregisterReceiver(getActivity());
    }

    private void l()
    {
        this.f.setSystemUIChangeListener(new ScribbleFragment.6(this));
        this.f.registerReceiver(getActivity());
    }

    private void m()
    {
        if (this.d == null) {
            this.d = new ScribbleFragment.7(this);
        }
        this.c.surfaceView.getHolder().addCallback(this.d);
        TouchAreaIgnoreAction.setFullScreenTouchAreaIgnoreRegion();
    }

    private void n()
    {
        r().getHandlerManager().reset();
        this.g = UUID.randomUUID().toString();
        new CreateDocumentAction(s()).setParentUniqueId(this.h).setDocumentUniqueId(this.g).setOptionArgs(DocumentOptionArgs.create()).execute(null);
    }

    public static ScribbleFragment newInstance(String paramString1, String paramString2, String paramString3)
    {
        Bundle localBundle = new Bundle();
        localBundle.putString("documentId", paramString1);
        localBundle.putString("parentUniqueId", paramString2);
        localBundle.putString("note_title", paramString3);
        paramString1 = new ScribbleFragment();
        paramString1.setArguments(localBundle);
        return paramString1;
    }

    private void o()
    {
        if (StringUtils.isNotBlank(this.g))
        {
            a(this.g, this.h);
            return;
        }
        n();
    }

    private void p()
    {
        new SaveDocumentAction(s()).setTitle(r().getNoteTitle()).setCloseAfterSave(true).exportAfterSave(this.g).enableProgress(getActivity(), 2131492990).execute(new ScribbleFragment.9(this));
    }

    private void q()
    {
        String str = r().getNoteTitle();
        r().quit();
        LibraryDataBundle.getInstance().post(new RefreshLibrariesEvent());
        getActivity().finish();
        b(str);
    }

    private NoteDataBundle r()
    {
        return NoteDataBundle.getInstance();
    }

    private NoteManager s()
    {
        return r().getNoteManager();
    }

    public ScribbleMenuModel getScribbleMenuModel()
    {
        return this.e;
    }

    public boolean onBackPressedSupport()
    {
        if (!this.i.isValid()) {
            return true;
        }
        boolean bool = r().getHandlerManager().onBackPressed();
        if ((!getScribbleMenuModel().onBackPressed(getActivity())) && (!bool)) {
            p();
        }
        return true;
    }

    public void onCreate(@Nullable Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        paramBundle = getArguments();
        if (paramBundle != null)
        {
            this.g = paramBundle.getString("documentId");
            this.h = paramBundle.getString("parentUniqueId");
            r().setNoteTitle(paramBundle.getString("note_title"));
        }
        s().getEventBus().register(this);
        r().getPenEventHandler().subscribe();
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
    {
        this.c = ((FragmentScribbleBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131361847, paramViewGroup, false));
        this.e = new ScribbleMenuModel(r().getEventBus());
        this.c.setModel(this.e);
        return this.c.getRoot();
    }

    public void onDestroy()
    {
        super.onDestroy();
        k();
        s().getEventBus().unregister(this);
        r().getPenEventHandler().unSubscribe();
        CapacitanceTpEnableAction.enable(getActivity());
        TouchAreaIgnoreAction.resetTouchAreaIgnore();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        switch (paramInt)
        {
            default:
                return false;
            case 93:
                this.e.onNextPage(this.c.getRoot());
                return true;
        }
        this.e.onPrevPage(this.c.getRoot());
        return true;
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onNoteInfoEvent(NoteInfoEvent paramNoteInfoEvent)
    {
        r().setNoteInfo(paramNoteInfoEvent.noteInfo);
        if (r().isDocOpened()) {
            getScribbleMenuModel().funcBarMenuModel.setPage(r().getPageInfo());
        }
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onOpenDocumentRequest(OpenDocumentRequestEvent paramOpenDocumentRequestEvent)
    {
        paramOpenDocumentRequestEvent = paramOpenDocumentRequestEvent.getNoteModel();
        if ((StringUtils.isNotBlank(this.g)) && (this.g.equals(paramOpenDocumentRequestEvent.getUniqueId()))) {
            return;
        }
        new SaveDocumentAction(s()).setTitle(r().getNoteTitle()).enableProgress(getActivity(), 2131492990).execute(new ScribbleFragment.2(this, paramOpenDocumentRequestEvent));
    }

    public void onPause()
    {
        super.onPause();
        r().getPenEventHandler().setPause(true);
        new RefreshDrawScreenAction(s()).execute(null);
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onQuitScribble(QuitScribbleEvent paramQuitScribbleEvent)
    {
        onBackPressedSupport();
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onResizeMenu(ResizeMenuEvent paramResizeMenuEvent)
    {
        RxView.globalLayoutObservable(this.c.workLayout, true).subscribe(new ScribbleFragment.1(this));
    }

    public void onResume()
    {
        super.onResume();
        r().getPenEventHandler().setPause(false);
        if (r().isDocOpened()) {
            new RefreshDrawScreenAction(s()).execute(null);
        }
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onSpanViewEnable(SpanViewEnableEvent paramSpanViewEnableEvent)
    {
        LinedEditText localLinedEditText = this.c.spanTextView;
        int j;
        if (paramSpanViewEnableEvent.enable) {
            j = 0;
        } else {
            j = 8;
        }
        localLinedEditText.setVisibility(j);
        if (paramSpanViewEnableEvent.enable) {
            s().post(new SpanViewEvent(this.c.spanTextView));
        }
    }

    public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
        b();
    }
}
