package com.ksyzt.gwt.client.ui.fileupload;

import java.util.Timer;
import java.util.TimerTask;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;

// TODO: Auto-generated Javadoc
/**
 * 文件上传.
 *
 * @author zhangjianshe@gmail.com
 */
public class SingleFileUpload extends MessageComposite {

	/** The ui binder. */
	private static SingleFileUploadUiBinder uiBinder = GWT
			.create(SingleFileUploadUiBinder.class);

	/**
	 * The Interface SingleFileUploadUiBinder.
	 */
	interface SingleFileUploadUiBinder extends
			UiBinder<Widget, SingleFileUpload> {
	}

	/**
	 * Instantiates a new single file upload.
	 */
	public SingleFileUpload() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.UIObject#setTitle(java.lang.String)
	 */
	public void setTitle(String caption) {
		lbTitle.setText(caption);
	}

	/** The file. */
	@UiField
	FileUpload file;

	/** The frm upload. */
	@UiField
	FormPanel frmUpload;
	
	/** The lb message. */
	@UiField
	Label lbMessage;
	
	/** The lb title. */
	@UiField
	Label lbTitle;
	
	/** The btn submit. */
	@UiField
	Button btnSubmit;

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.common.MessageComposite#message(java.lang.String)
	 */
	public void message(String msg) {
		lbMessage.setText(msg);
	}

	/** The m file change. */
	private ChangeHandler mFileChange = new ChangeHandler() {

		@Override
		public void onChange(ChangeEvent event) {
			String fn = file.getFilename();
			if (fn.equals("")) {
				btnSubmit.setEnabled(false);
			} else {
				btnSubmit.setEnabled(true);
			}
		}
	};

	/** The m submit handler. */
	private SubmitHandler mSubmitHandler = new SubmitHandler() {

		@Override
		public void onSubmit(SubmitEvent event) {

		}
	};

	/** The m complete. */
	private SubmitCompleteHandler mComplete = new SubmitCompleteHandler() {

		@Override
		public void onSubmitComplete(SubmitCompleteEvent event) {

		}
	};

	/** The m timer. */
	Timer mTimer ;

	/**
	 * 设置监听上传进度.
	 *
	 * @param b the b
	 */
	private void enableWatch(boolean b) {
		final String url=GWT.getModuleBaseURL() + "../fileuploader?uploadStatus=true";
		if (b == true) {
			if(mTimer!=null)
			{
				
			}
			else
			{
				mTimer =new Timer();
				mTimer.schedule(new TimerTask() {	
					@Override
					public void run() {
						RequestBuilder rb=new RequestBuilder(RequestBuilder.POST, url);
						try {
							rb.sendRequest("", new RequestCallback() {
								
								@Override
								public void onResponseReceived(Request request, Response response) {
									if(response.getStatusCode()==Response.SC_OK)
									{
										
										String jsonstr = response.getText();
										JSONValue rvalue = JSONParser.parse(jsonstr);
										JSONObject robj = rvalue.isObject();
										
										String error = robj.get("error").isString().stringValue();
										String statusMsg = robj.get("statusMsg").isString().stringValue();
										String readedBytes = robj.get("readedBytes").isString().stringValue();
										String totalBytes = robj.get("totalBytes").isString().stringValue();
										String currentItem = robj.get("currentItem").isString().stringValue();
										String filePath = robj.get("filePath").isString().stringValue();
										
										message("上传进度："+(100*Float.parseFloat(readedBytes))/Float.parseFloat(totalBytes)+"%");

									}
										
									
								}
								
								@Override
								public void onError(Request request, Throwable exception) {
									
								}
							});
						} catch (RequestException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 0, 700);
			}
		}else
		{
			if(mTimer!=null)
			{
				mTimer.cancel();
				mTimer=null;
			}
			else
			{
				
			}
		}
	}

	/**
	 * Inits the.
	 */
	private void init() {
		btnSubmit.setEnabled(false);
		file.setName("filename");
		file.addChangeHandler(mFileChange);

		frmUpload.setEncoding(FormPanel.ENCODING_MULTIPART);
		frmUpload.setMethod(FormPanel.METHOD_POST);
		frmUpload.setAction(GWT.getModuleBaseURL() + "../fileuploader");
		frmUpload.addSubmitHandler(mSubmitHandler);
		frmUpload.addSubmitCompleteHandler(mComplete);
	}
}
