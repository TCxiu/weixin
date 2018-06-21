package com.xiu.wserver.controller;

import com.jfinal.weixin.iot.msg.InEquDataMsg;
import com.jfinal.weixin.iot.msg.InEqubindEvent;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.*;
import com.jfinal.weixin.sdk.msg.in.card.*;
import com.jfinal.weixin.sdk.msg.in.event.*;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutImageMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.jfinal.weixin.sdk.msg.out.OutVideoMsg;
import com.jfinal.weixin.sdk.msg.out.OutVoiceMsg;
import net.dreamlu.weixin.annotation.WxMsgController;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.beans.factory.annotation.Autowired;

@WxMsgController("/dyd/wx")
public class WeixinController extends MsgControllerAdapter {

    @Autowired
    private DreamWeixinProperties weixinProperties;

    /**
     * @method 方法名: processInFollowEvent
     * @Decription 方法描述: 关注/取消关注时触发此方法
     *
     * @params 传入参数:[inFollowEvent]
     * @return 返回值类型:void
     * @throws
     */
    @Override
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
        String event = inFollowEvent.getEvent();
        System.out.println(event);
        outMsg.setContent("关注消息~");
        render(outMsg);
    }

    /**
     * @method 方法名: processInTextMsg
     * @Decription 方法描述: 用户输入文本消息时触发此消息
     *
     * @params 传入参数:[inTextMsg]
     * @return 返回值类型:void
     * @throws
     */
    @Override
    protected void processInTextMsg(InTextMsg inTextMsg) {
        String content = inTextMsg.getContent();
        String fromUserName = inTextMsg.getFromUserName();

        OutTextMsg outMsg = new OutTextMsg(inTextMsg);

        if (content != null  & ! "".equals(content.trim()) & content.contains("邀请码") ){
            outMsg.setContent("恭喜:"+fromUserName+"获得邀请码:"+"A8A8");
        }
        else {
            outMsg.setContent(inTextMsg.getContent());
        }
        render(outMsg);
    }

    /**
     * @method 方法名: processInMenuEvent
     * @Decription 方法描述: 用户点击菜单触发此方法
     *
     * @params 传入参数:[inMenuEvent]
     * @return 返回值类型:void
     * @throws
     */
    @Override
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
        String fromUserName = inMenuEvent.getFromUserName();
        String toUserName = inMenuEvent.getToUserName();

        outMsg.setContent("事件"+inMenuEvent.getEvent()+"来自用户:"+fromUserName+"\t去往:"+toUserName);
        render(outMsg);
    }

    /**
     * @method 方法名: processInImageMsg
     * @Decription 方法描述:  用户发送图片消息时触发此方法
     *
     * @params 传入参数:[inImageMsg]
     * @return 返回值类型:void
     * @throws
     */
    @Override
    protected void processInImageMsg(InImageMsg inImageMsg) {
        OutImageMsg outImageMsg = new OutImageMsg(inImageMsg);
        render(outImageMsg);
    }

    /**
     * @method 方法名: processInVoiceMsg
     * @Decription 方法描述: 用户发送语音消息时触发此方法
     *
     * @params 传入参数:[inVoiceMsg]
     * @return 返回值类型:void
     * @throws
     */
    @Override
    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
        OutVoiceMsg outVoiceMsg = new OutVoiceMsg(inVoiceMsg);
        render(outVoiceMsg);
    }

    /**
     * @method 方法名: processInVoiceMsg
     * @Decription 方法描述: 用户发送视频消息时触发此方法
     *
     * @params 传入参数:[inVoiceMsg]
     * @return 返回值类型:void
     * @throws
     */
    @Override
    protected void processInVideoMsg(InVideoMsg inVideoMsg) {
        OutVideoMsg outVideoMsg = new OutVideoMsg(inVideoMsg);
        outVideoMsg.setMediaId(inVideoMsg.getMediaId());
        outVideoMsg.setMsgType("image");
        render(outVideoMsg);
    }

    @Override
    protected void processInLocationMsg(InLocationMsg inLocationMsg) {
        super.processInLocationMsg(inLocationMsg);
    }

    @Override
    protected void processInLinkMsg(InLinkMsg inLinkMsg) {
        super.processInLinkMsg(inLinkMsg);
    }

    @Override
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        super.processInQrCodeEvent(inQrCodeEvent);
    }

    @Override
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {
        super.processInLocationEvent(inLocationEvent);
    }

    @Override
    protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
        super.processInSpeechRecognitionResults(inSpeechRecognitionResults);
    }

    @Override
    protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
        super.processInTemplateMsgEvent(inTemplateMsgEvent);
    }

    @Override
    protected void processInMassEvent(InMassEvent inMassEvent) {
        super.processInMassEvent(inMassEvent);
    }

    @Override
    protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg) {
        super.processInShortVideoMsg(inShortVideoMsg);
    }

    @Override
    protected void processInCustomEvent(InCustomEvent inCustomEvent) {
        super.processInCustomEvent(inCustomEvent);
    }

    @Override
    protected void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {
        super.processInShakearoundUserShakeEvent(inShakearoundUserShakeEvent);
    }

    @Override
    protected void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {
        super.processInVerifySuccessEvent(inVerifySuccessEvent);
    }

    @Override
    protected void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent) {
        super.processInVerifyFailEvent(inVerifyFailEvent);
    }

    @Override
    protected void processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent) {
        super.processInPoiCheckNotifyEvent(inPoiCheckNotifyEvent);
    }

    @Override
    protected void processInWifiEvent(InWifiEvent inWifiEvent) {
        super.processInWifiEvent(inWifiEvent);
    }

    @Override
    protected void processInUpdateMemberCardEvent(InUpdateMemberCardEvent msg) {
        super.processInUpdateMemberCardEvent(msg);
    }

    @Override
    protected void processInUserPayFromCardEvent(InUserPayFromCardEvent msg) {
        super.processInUserPayFromCardEvent(msg);
    }

    @Override
    protected void processInMerChantOrderEvent(InMerChantOrderEvent inMerChantOrderEvent) {
        super.processInMerChantOrderEvent(inMerChantOrderEvent);
    }

    @Override
    protected void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent) {
        super.processIsNotDefinedEvent(inNotDefinedEvent);
    }

    @Override
    protected void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg) {
        super.processIsNotDefinedMsg(inNotDefinedMsg);
    }

    @Override
    protected void processInUserGiftingCardEvent(InUserGiftingCardEvent msg) {
        super.processInUserGiftingCardEvent(msg);
    }

    @Override
    protected void processInUserGetCardEvent(InUserGetCardEvent msg) {
        super.processInUserGetCardEvent(msg);
    }

    @Override
    protected void processInUserConsumeCardEvent(InUserConsumeCardEvent msg) {
        super.processInUserConsumeCardEvent(msg);
    }

    @Override
    protected void processInCardSkuRemindEvent(InCardSkuRemindEvent msg) {
        super.processInCardSkuRemindEvent(msg);
    }

    @Override
    protected void processInCardPayOrderEvent(InCardPayOrderEvent msg) {
        super.processInCardPayOrderEvent(msg);
    }

    @Override
    protected void processInCardPassCheckEvent(InCardPassCheckEvent msg) {
        super.processInCardPassCheckEvent(msg);
    }

    @Override
    protected void processInUserCardEvent(InUserCardEvent inUserCardEvent) {
        super.processInUserCardEvent(inUserCardEvent);
    }

    @Override
    protected void processInEqubindEvent(InEqubindEvent msg) {
        super.processInEqubindEvent(msg);
    }

    @Override
    protected void processInEquDataMsg(InEquDataMsg msg) {
        super.processInEquDataMsg(msg);
    }
}
