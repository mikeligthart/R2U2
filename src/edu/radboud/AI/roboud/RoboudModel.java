package edu.radboud.AI.roboud;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.AudioRecord;
import com.wowwee.robome.RoboMeCommands.IncomingRobotCommand;
import com.wowwee.robome.RoboMeCommands.RobotCommand;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Gebruiker on 13-5-14.
 */
public class RoboudModel extends Observable {

    private boolean robomeConnected, robomeHeadsetPluggedIn, listening;
    private float volume;
    private String libVersion;

    private Bitmap image;
    private int faces;
    private float[] rotation, linearAcceleration, gravity, gyro, magneticField;
    private float proximity, light;
    private Location loc;
    private AudioRecord audioRecord;

    private List<IncomingRobotCommand> incomingCommands;
    private List<RobotCommand> outgoingCommands;

    private long lastModification;

    public RoboudModel(String _libVersion) {
        this.libVersion = _libVersion;
        robomeConnected = false;
        robomeHeadsetPluggedIn = false;
        listening = false;
        volume = -1;
        image = null;
        faces = 0;
        rotation = new float[3];
        linearAcceleration = new float[3];
        gravity = new float[3];
        gyro = new float[3];
        magneticField = new float[6];
        proximity = -1;
        light = -1;
        loc = null;
        audioRecord = null;

        incomingCommands = new LinkedList<IncomingRobotCommand>();
        outgoingCommands = new LinkedList<RobotCommand>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RoboudModel").append("\n");
        sb.append("Connected to RoboMe: ").append(robomeConnected).append("\n");
        sb.append("Headset plugged in: ").append(robomeHeadsetPluggedIn).append("\n");
        sb.append("Listening to RoboMe: ").append(listening).append("\n");
        sb.append("Volume: ").append(volume).append("\n");
        sb.append("Library version: ").append(libVersion).append("\n");
        if (image != null)
            sb.append("Image: ").append(image.getWidth()).append("x").append(image.getHeight()).append("\n");
        else
            sb.append("Image: ").append("null").append("\n");
        sb.append("Number of faces: ").append(faces).append("\n");
        sb.append("Rotation: ").append(rotation[0]).append(" \t").append(rotation[1]).append(" \t").append(rotation[2])
                .append("\n");
        sb.append("Linear acceleration: ").append(linearAcceleration[0]).append(" \t").append(linearAcceleration[1])
                .append("\t").append(linearAcceleration[2]).append("\n");
        sb.append("Gravity: ").append(gravity[0]).append(" \t").append(gravity[1]).append(" \t").append(gravity[2])
                .append("\n");
        sb.append("Gyro: ").append(gyro[0]).append(" \t").append(gyro[1]).append(" \t").append(gyro[2]).append("\n");
        sb.append("Magnetic field: ").append(magneticField[0]).append(" \t").append(magneticField[1]).append("\t")
                .append(magneticField[2]).append("\n");
        sb.append("Proximity: ").append(proximity).append("\n");
        sb.append("Light: ").append(light).append("\n");
        if (loc != null)
            sb.append("Location: ").append(loc.getLatitude()).append(" \t").append(loc.getLongitude()).append("\n");
        else
            sb.append("Location: ").append("null");
        if (audioRecord != null)
            sb.append("Audio record: ").append(audioRecord.getAudioFormat()).append(" \t")
                    .append(audioRecord.getSampleRate()).append("x").append(audioRecord.getChannelCount());
        else
            sb.append("Audio record: ").append("null").append("\n");
        sb.append("Incoming command count: ").append(incomingCommands.size()).append("\n");
        sb.append("Outgoing command count: ").append(outgoingCommands.size()).append("\n");
        return sb.toString();
    }

    public void changed() {
        setChanged();
        notifyObservers();
        lastModification = System.currentTimeMillis();
    }

    public long getLastModification() {
        return lastModification;
    }

    //      === START RoboMe part ===

    public String getLibVersion() {
        return libVersion;
    }

    public void setRobomeConnected(boolean robomeConnected) {
        this.robomeConnected = robomeConnected;
        changed();
    }

    public boolean isRobomeConnected() {
        return robomeConnected;
    }

    public boolean isRobomeHeadsetPluggedIn() {
        return robomeHeadsetPluggedIn;
    }

    public void setRobomeHeadsetPluggedIn(boolean robomeHeadsetPluggedIn) {
        this.robomeHeadsetPluggedIn = robomeHeadsetPluggedIn;
        changed();
    }

    public void setVolume(float volume) {
        this.volume = volume;
        changed();
    }

    public float getVolume() {
        return volume;
    }

    public boolean isListening() {
        return listening;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
        changed();
    }

    public void receiveCommand(IncomingRobotCommand incomingRobotCommand) {
        incomingCommands.add(incomingRobotCommand);
        changed();
    }

    public void sendCommand(RobotCommand outgoingCommand) {
        outgoingCommands.add(outgoingCommand);
        changed();
    }

    //      === END RoboMe part ===

    //      === START Android phone part ===


    public void setImage(Bitmap image) {
        this.image = image;
        changed();
    }

    public Bitmap getImage() {
        return image;
    }

    public float[] getRotation() {
        return rotation;
    }

    public void setRotation(float[] rotation) {
        this.rotation = rotation;
        changed();
    }

    public float[] getLinearAcceleration() {
        return linearAcceleration;
    }

    public void setLinearAcceleration(float[] linearAcceleration) {
        this.linearAcceleration = linearAcceleration;
        changed();
    }

    public float[] getGravity() {
        return gravity;
    }

    public void setGravity(float[] gravity) {
        this.gravity = gravity;
        changed();
    }

    public float[] getGyro() {
        return gyro;
    }

    public void setGyro(float[] gyro) {
        this.gyro = gyro;
        changed();
    }

    public float[] getMagneticField() {
        return magneticField;
    }

    public void setMagneticField(float[] magneticField) {
        this.magneticField = magneticField;
        changed();
    }

    public float getProximity() {
        return proximity;
    }

    public void setProximity(float proximity) {
        this.proximity = proximity;
        changed();
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
        changed();
    }

    public void setLocation(Location location) {
        this.loc = location;
        changed();
    }

    public Location getLocation() {
        return loc;
    }

    public void setFaces(int faces) {
        this.faces = faces;
        changed();
    }

    public int getFaces() {
        return faces;
    }

    public void setAudioRecord(AudioRecord audioRecord) {
        this.audioRecord = audioRecord;
        changed();
    }

    public AudioRecord getAudioRecord() {
        return audioRecord;
    }

    //      == END Android phone part ===
}