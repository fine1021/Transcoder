package net.ypresto.androidtranscoder.validator;

/**
 * The default {@link Validator} to understand whether to keep going with the
 * transcoding process or to abort and notify the listener.
 */
public class DefaultValidator implements Validator {

    @Override
    public boolean validate(TrackStatus videoStatus, TrackStatus audioStatus) {
        if (videoStatus == TrackStatus.COMPRESSING || audioStatus == TrackStatus.COMPRESSING) {
            // If someone is compressing, keep going.
            return true;
        }
        // Both tracks are either absent, passthrough or being removed. Would be tempted
        // to return false here, however a removal might be a intentional action: Keep going.
        if (videoStatus == TrackStatus.REMOVING || audioStatus == TrackStatus.REMOVING) {
            return true;
        }

        // At this point it's either ABSENT or PASS_THROUGH so we are safe aborting
        // the process.
        return false;
    }
}
