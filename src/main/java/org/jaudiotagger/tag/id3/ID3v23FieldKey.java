package org.jaudiotagger.tag.id3;

import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTXXX;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUFID;
import org.jaudiotagger.tag.id3.framebody.FrameBodyWXXX;
import org.jaudiotagger.tag.id3.valuepair.StandardIPLSKey;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * List of known id3v23 metadata fields
 *
 * <p>These provide a mapping from the generic key to the underlying ID3v23frames. For example most of the Musicbrainz
 * fields are implemented using a User Defined Text Info Frame, but with a different description key, so this
 * enum provides the link between the two.
 */
public enum ID3v23FieldKey
{


    ACOUSTID_FINGERPRINT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ACOUSTID_FINGERPRINT, Id3FieldType.TEXT),
    ACOUSTID_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ACOUSTID_ID, Id3FieldType.TEXT),
    ALBUM(ID3v23Frames.FRAME_ID_V3_ALBUM, Id3FieldType.TEXT),
    ALBUM_ARTIST(ID3v23Frames.FRAME_ID_V3_ACCOMPANIMENT, Id3FieldType.TEXT),
    ALBUM_ARTIST_SORT(ID3v23Frames.FRAME_ID_V3_ALBUM_ARTIST_SORT_ORDER_ITUNES, Id3FieldType.TEXT),
    ALBUM_ARTISTS(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ALBUM_ARTISTS, Id3FieldType.TEXT),
    ALBUM_ARTISTS_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ALBUM_ARTISTS_SORT, Id3FieldType.TEXT),
    ALBUM_SORT(ID3v23Frames.FRAME_ID_V3_ALBUM_SORT_ORDER_ITUNES, Id3FieldType.TEXT),
    ALBUM_YEAR(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ALBUM_YEAR, Id3FieldType.TEXT),
    AMAZON_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.AMAZON_ASIN, Id3FieldType.TEXT),
    ARRANGER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ARRANGER, Id3FieldType.TEXT),
    ARRANGER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ARRANGER_SORT, Id3FieldType.TEXT),
    ARRANGER_INVOLVEDPEOPLE(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE,  StandardIPLSKey.ARRANGER.getKey(), Id3FieldType.TEXT),
    ARTIST(ID3v23Frames.FRAME_ID_V3_ARTIST, Id3FieldType.TEXT),
    ARTISTS(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ARTISTS, Id3FieldType.TEXT),
    ARTISTS_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ARTISTS_SORT, Id3FieldType.TEXT),
    ARTIST_SORT(ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_ITUNES, Id3FieldType.TEXT),
    BARCODE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.BARCODE, Id3FieldType.TEXT),
    AUDIO_ENGINEER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.AUDIO_ENGINEER, Id3FieldType.TEXT),
    AUDIO_ENGINEER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.AUDIO_ENGINEER_SORT, Id3FieldType.TEXT),
    BALANCE_ENGINEER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.BALANCE_ENGINEER, Id3FieldType.TEXT),
    BALANCE_ENGINEER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.BALANCE_ENGINEER_SORT, Id3FieldType.TEXT),
    BPM(ID3v23Frames.FRAME_ID_V3_BPM, Id3FieldType.TEXT),
    CATALOG_NO(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.CATALOG_NO, Id3FieldType.TEXT),
    CHOIR(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.CHOIR, Id3FieldType.TEXT),
    CHOIR_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.CHOIR_SORT, Id3FieldType.TEXT),
    CLASSICAL_CATALOG(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.CLASSICAL_CATALOG, Id3FieldType.TEXT),
    CLASSICAL_NICKNAME(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.CLASSICAL_NICKNAME, Id3FieldType.TEXT),
    COMMENT(ID3v23Frames.FRAME_ID_V3_COMMENT, Id3FieldType.TEXT),
    COMPOSER(ID3v23Frames.FRAME_ID_V3_COMPOSER, Id3FieldType.TEXT),
    COMPOSER_SORT(ID3v23Frames.FRAME_ID_V3_COMPOSER_SORT_ORDER_ITUNES, Id3FieldType.TEXT),
    CONDUCTOR(ID3v23Frames.FRAME_ID_V3_CONDUCTOR, Id3FieldType.TEXT),
    CONDUCTOR_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.CONDUCTOR_SORT, Id3FieldType.TEXT),
    COPYRIGHT(ID3v23Frames.FRAME_ID_V3_COPYRIGHTINFO, Id3FieldType.TEXT),
    COUNTRY(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.COUNTRY, Id3FieldType.TEXT),
    COVER_ART(ID3v23Frames.FRAME_ID_V3_ATTACHED_PICTURE, Id3FieldType.BINARY),
    CREDITS(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.CREDITS, Id3FieldType.TEXT),
    CUSTOM1(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_CUSTOM1,Id3FieldType.TEXT),
    CUSTOM2(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_CUSTOM2,Id3FieldType.TEXT),
    CUSTOM3(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_CUSTOM3,Id3FieldType.TEXT),
    CUSTOM4(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_CUSTOM4,Id3FieldType.TEXT),
    CUSTOM5(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_CUSTOM5,Id3FieldType.TEXT),
    DISC_NO(ID3v23Frames.FRAME_ID_V3_SET, Id3FieldType.TEXT),
    DISC_SUBTITLE(ID3v23Frames.FRAME_ID_V3_SET_SUBTITLE ,Id3FieldType.TEXT),
    DISC_TOTAL(ID3v23Frames.FRAME_ID_V3_SET, Id3FieldType.TEXT),
    DJMIXER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.DJMIXER, Id3FieldType.TEXT),
    DJMIXER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.DJMIXER_SORT, Id3FieldType.TEXT),
    DJMIXER_INVOLVEDPEOLE(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE,  StandardIPLSKey.DJMIXER.getKey(), Id3FieldType.TEXT),
    ENCODER(ID3v23Frames.FRAME_ID_V3_ENCODEDBY, Id3FieldType.TEXT),
    ENGINEER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ENGINEER, Id3FieldType.TEXT),
    ENGINEER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ENGINEER_SORT, Id3FieldType.TEXT),
    ENGINEER_INVOLVEDPEOPLE(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE,  StandardIPLSKey.ENGINEER.getKey(), Id3FieldType.TEXT),
    ENSEMBLE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ENSEMBLE, Id3FieldType.TEXT),
    ENSEMBLE_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ENSEMBLE_SORT, Id3FieldType.TEXT),
    FBPM(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.FBPM, Id3FieldType.TEXT),
    GENRE(ID3v23Frames.FRAME_ID_V3_GENRE, Id3FieldType.TEXT),
    GROUP(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.GROUP, Id3FieldType.TEXT),
    GROUPING(ID3v23Frames.FRAME_ID_V3_CONTENT_GROUP_DESC, Id3FieldType.TEXT),
    INVOLVEDPEOPLE(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE, Id3FieldType.TEXT),
    INSTRUMENT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.INSTRUMENT, Id3FieldType.TEXT),
    IPI(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.IPI, Id3FieldType.TEXT),
    ISRC(ID3v23Frames.FRAME_ID_V3_ISRC, Id3FieldType.TEXT),
    ISWC(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ISWC, Id3FieldType.TEXT),
    IS_CLASSICAL(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.IS_CLASSICAL, Id3FieldType.TEXT),
    IS_COMPILATION(ID3v23Frames.FRAME_ID_V3_IS_COMPILATION, Id3FieldType.TEXT),
    IS_GREATEST_HITS(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.IS_GREATEST_HITS, Id3FieldType.TEXT),
    IS_SOUNDTRACK(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.IS_SOUNDTRACK, Id3FieldType.TEXT),
    IS_HD(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.IS_HD, Id3FieldType.TEXT),
    IS_LIVE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.LIVE, Id3FieldType.TEXT),
    ITUNES_GROUPING(ID3v23Frames.FRAME_ID_V3_ITUNES_GROUPING, Id3FieldType.TEXT),
    JAIKOZ_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.JAIKOZ_ID, Id3FieldType.TEXT),
    KEY(ID3v23Frames.FRAME_ID_V3_INITIAL_KEY,Id3FieldType.TEXT),
    LANGUAGE(ID3v23Frames.FRAME_ID_V3_LANGUAGE,Id3FieldType.TEXT),
    LYRICIST(ID3v23Frames.FRAME_ID_V3_LYRICIST, Id3FieldType.TEXT),
    LYRICIST_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.LYRICIST_SORT, Id3FieldType.TEXT),
    LYRICS(ID3v23Frames.FRAME_ID_V3_UNSYNC_LYRICS, Id3FieldType.TEXT),
    MASTERING(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MASTERING, Id3FieldType.TEXT),
    MASTERING_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MASTERING_SORT, Id3FieldType.TEXT),
    MEDIA(ID3v23Frames.FRAME_ID_V3_MEDIA_TYPE, Id3FieldType.TEXT),
    MIXER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MIXER, Id3FieldType.TEXT),
    MIXER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MIXER_SORT, Id3FieldType.TEXT),
    MIXER_INVOLVEDPEOLE(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE,  StandardIPLSKey.MIXER.getKey(), Id3FieldType.TEXT),
    MOOD(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD, Id3FieldType.TEXT),
    MOOD_ACOUSTIC(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_ACOUSTIC, Id3FieldType.TEXT),
    MOOD_AGGRESSIVE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_AGGRESSIVE, Id3FieldType.TEXT),
    MOOD_AROUSAL(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_AROUSAL, Id3FieldType.TEXT),
    MOOD_DANCEABILITY(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_DANCEABILITY, Id3FieldType.TEXT),
    MOOD_ELECTRONIC(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_ELECTRONIC, Id3FieldType.TEXT),
    MOOD_HAPPY(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_HAPPY, Id3FieldType.TEXT),
    MOOD_INSTRUMENTAL(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_INSTRUMENTAL, Id3FieldType.TEXT),
    MOOD_PARTY(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_PARTY, Id3FieldType.TEXT),
    MOOD_RELAXED(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_RELAXED, Id3FieldType.TEXT),
    MOOD_SAD(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_SAD, Id3FieldType.TEXT),
    MOOD_VALENCE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MOOD_VALENCE, Id3FieldType.TEXT),
    MOVEMENT(ID3v23Frames.FRAME_ID_V3_MOVEMENT, Id3FieldType.TEXT),
    MOVEMENT_NO(ID3v23Frames.FRAME_ID_V3_MOVEMENT_NO, Id3FieldType.TEXT),
    MOVEMENT_TOTAL(ID3v23Frames.FRAME_ID_V3_MOVEMENT_NO, Id3FieldType.TEXT),
    MUSICBRAINZ_ARTISTID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_ARTISTID, Id3FieldType.TEXT),
    MUSICBRAINZ_DISC_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_DISCID, Id3FieldType.TEXT),
    MUSICBRAINZ_ORIGINAL_RELEASEID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_ORIGINAL_ALBUMID, Id3FieldType.TEXT),
    MUSICBRAINZ_RELEASEARTISTID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_ALBUM_ARTISTID, Id3FieldType.TEXT),
    MUSICBRAINZ_RELEASEID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_ALBUMID, Id3FieldType.TEXT),
    MUSICBRAINZ_RELEASE_COUNTRY(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_ALBUM_COUNTRY, Id3FieldType.TEXT),
    MUSICBRAINZ_RELEASE_GROUP_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_RELEASE_GROUPID, Id3FieldType.TEXT),
    MUSICBRAINZ_RELEASE_STATUS(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_ALBUM_STATUS, Id3FieldType.TEXT),
    MUSICBRAINZ_RELEASE_TRACK_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_RELEASE_TRACKID, Id3FieldType.TEXT),
    MUSICBRAINZ_RELEASE_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_ALBUM_TYPE, Id3FieldType.TEXT),
    MUSICBRAINZ_TRACK_ID(ID3v23Frames.FRAME_ID_V3_UNIQUE_FILE_ID, FrameBodyUFID.UFID_MUSICBRAINZ, Id3FieldType.TEXT),
    MUSICBRAINZ_RECORDING_WORK(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_RECORDING_WORK, Id3FieldType.TEXT),
    MUSICBRAINZ_RECORDING_WORK_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_RECORDING_WORK_ID, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORKID, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK_PART_LEVEL1_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1_ID, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK_PART_LEVEL2_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2_ID, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK_PART_LEVEL3_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3_ID, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK_PART_LEVEL4_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4_ID, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK_PART_LEVEL5_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5_ID, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK_PART_LEVEL6_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6_ID, Id3FieldType.TEXT),
    MUSICIP_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICIP_ID, Id3FieldType.TEXT),
    OCCASION(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_OCCASION,Id3FieldType.TEXT),
    OPUS(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.OPUS, Id3FieldType.TEXT),
    ORCHESTRA(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ORCHESTRA, Id3FieldType.TEXT),
    ORCHESTRA_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ORCHESTRA_SORT, Id3FieldType.TEXT),
    ORIGINAL_ALBUM(ID3v23Frames.FRAME_ID_V3_ORIG_TITLE, Id3FieldType.TEXT),
    ORIGINALRELEASEDATE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ORIGINALRELEASEDATE, Id3FieldType.TEXT),
    ORIGINAL_ARTIST(ID3v23Frames.FRAME_ID_V3_ORIGARTIST, Id3FieldType.TEXT),
    ORIGINAL_LYRICIST(ID3v23Frames.FRAME_ID_V3_ORIG_LYRICIST, Id3FieldType.TEXT),
    OVERALL_WORK(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.OVERALL_WORK, Id3FieldType.TEXT),
    ORIGINAL_YEAR(ID3v23Frames.FRAME_ID_V3_TORY, Id3FieldType.TEXT),
    PART(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PART, Id3FieldType.TEXT),
    PART_NUMBER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PART_NUMBER, Id3FieldType.TEXT),
    PART_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PART_TYPE, Id3FieldType.TEXT),
    PERFORMER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PERFORMER, Id3FieldType.TEXT),
    PERFORMER_NAME(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PERFORMER_NAME, Id3FieldType.TEXT),
    PERFORMER_NAME_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PERFORMER_NAME_SORT, Id3FieldType.TEXT),
    PERIOD(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PERIOD, Id3FieldType.TEXT),
    PRODUCER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PRODUCER, Id3FieldType.TEXT),
    PRODUCER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.PRODUCER_SORT, Id3FieldType.TEXT),
    PRODUCER_INVOLVEDPEOPLE(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE,  StandardIPLSKey.PRODUCER.getKey(),Id3FieldType.TEXT),
    QUALITY(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_QUALITY,Id3FieldType.TEXT),
    RANKING(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.RANKING, Id3FieldType.TEXT),
    RATING(ID3v23Frames.FRAME_ID_V3_POPULARIMETER, Id3FieldType.TEXT),
    RECORD_LABEL(ID3v23Frames.FRAME_ID_V3_PUBLISHER, Id3FieldType.TEXT),
    RECORDING_ENGINEER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.RECORDING_ENGINEER, Id3FieldType.TEXT),
    RECORDING_ENGINEER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.RECORDING_ENGINEER_SORT, Id3FieldType.TEXT),
    RECORDINGDATE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.RECORDINGDATE, Id3FieldType.TEXT),
    RECORDINGSTARTDATE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.RECORDINGSTARTDATE, Id3FieldType.TEXT),
    RECORDINGENDDATE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.RECORDINGENDDATE, Id3FieldType.TEXT),
    RECORDINGLOCATION(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.RECORDINGLOCATION, Id3FieldType.TEXT),
    REMIXER(ID3v23Frames.FRAME_ID_V3_REMIXED, Id3FieldType.TEXT),
    ROONALBUMTAG(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ROONALBUMTAG, Id3FieldType.TEXT),
    ROONTRACKTAG(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.ROONTRACKTAG, Id3FieldType.TEXT),
    SCRIPT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.SCRIPT, Id3FieldType.TEXT),
    SECTION(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.SECTION, Id3FieldType.TEXT),
    SINGLE_DISC_TRACK_NO(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.SINGLE_DISC_TRACK_NO, Id3FieldType.TEXT),
    SONGKONG_ID(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.SONGKONG_ID, Id3FieldType.TEXT),
    SOUND_ENGINEER(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.SOUND_ENGINEER, Id3FieldType.TEXT),
    SOUND_ENGINEER_SORT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.SOUND_ENGINEER_SORT, Id3FieldType.TEXT),
    SUBTITLE(ID3v23Frames.FRAME_ID_V3_TITLE_REFINEMENT, Id3FieldType.TEXT),
    TAGS(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.TAGS, Id3FieldType.TEXT),
    TEMPO(ID3v23Frames.FRAME_ID_V3_COMMENT, FrameBodyCOMM.MM_TEMPO,Id3FieldType.TEXT),
    TIMBRE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.TIMBRE, Id3FieldType.TEXT),
    TITLE(ID3v23Frames.FRAME_ID_V3_TITLE, Id3FieldType.TEXT),
    TITLE_MOVEMENT(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.TITLE_MOVEMENT, Id3FieldType.TEXT),
    MUSICBRAINZ_WORK(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK, Id3FieldType.TEXT),
    TITLE_SORT(ID3v23Frames.FRAME_ID_V3_TITLE_SORT_ORDER_ITUNES, Id3FieldType.TEXT),
    TONALITY(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.TONALITY, Id3FieldType.TEXT),
    TRACK(ID3v23Frames.FRAME_ID_V3_TRACK, Id3FieldType.TEXT),
    TRACK_TOTAL(ID3v23Frames.FRAME_ID_V3_TRACK, Id3FieldType.TEXT),
    URL_BANDCAMP_ARTIST_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_BANDCAMP_ARTIST_SITE, Id3FieldType.TEXT),
    URL_BANDCAMP_RELEASE_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_BANDCAMP_RELEASE_SITE, Id3FieldType.TEXT),
    URL_DISCOGS_ARTIST_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_DISCOGS_ARTIST_SITE, Id3FieldType.TEXT),
    URL_DISCOGS_RELEASE_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_DISCOGS_RELEASE_SITE, Id3FieldType.TEXT),
    URL_LYRICS_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_LYRICS_SITE, Id3FieldType.TEXT),
    URL_OFFICIAL_ARTIST_SITE(ID3v23Frames.FRAME_ID_V3_URL_ARTIST_WEB, Id3FieldType.TEXT),
    URL_OFFICIAL_RELEASE_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_OFFICIAL_RELEASE_SITE, Id3FieldType.TEXT),
    URL_WIKIPEDIA_ARTIST_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_WIKIPEDIA_ARTIST_SITE, Id3FieldType.TEXT),
    URL_WIKIPEDIA_RELEASE_SITE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL, FrameBodyWXXX.URL_WIKIPEDIA_RELEASE_SITE, Id3FieldType.TEXT),
    WORK(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.WORK, Id3FieldType.TEXT),
    WORK_PART_LEVEL1(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1, Id3FieldType.TEXT),
    WORK_PART_LEVEL1_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE, Id3FieldType.TEXT),
    WORK_PART_LEVEL2(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2, Id3FieldType.TEXT),
    WORK_PART_LEVEL2_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE, Id3FieldType.TEXT),
    WORK_PART_LEVEL3(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3, Id3FieldType.TEXT),
    WORK_PART_LEVEL3_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE, Id3FieldType.TEXT),
    WORK_PART_LEVEL4(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4, Id3FieldType.TEXT),
    WORK_PART_LEVEL4_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE, Id3FieldType.TEXT),
    WORK_PART_LEVEL5(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5, Id3FieldType.TEXT),
    WORK_PART_LEVEL5_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE, Id3FieldType.TEXT),
    WORK_PART_LEVEL6(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6, Id3FieldType.TEXT),
    WORK_PART_LEVEL6_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE, Id3FieldType.TEXT),
    WORK_TYPE(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.WORK_TYPE, Id3FieldType.TEXT),
    VERSION(ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO, FrameBodyTXXX.VERSION, Id3FieldType.TEXT),
    YEAR(ID3v23Frames.FRAME_ID_V3_TYER, Id3FieldType.TEXT),
    ;
    private String fieldName;

    private String frameId;
    private String subId;
    private Id3FieldType fieldType;

    /**
     * For usual metadata fields that use a data field
     *
     * @param frameId   the frame that will be used
     * @param fieldType of data atom
     */
    ID3v23FieldKey(String frameId, Id3FieldType fieldType)
    {
        this.frameId = frameId;
        this.fieldType = fieldType;

        this.fieldName = frameId;
    }

    /**
     * @param frameId   the frame that will be used
     * @param subId     the additional key required within the frame to uniquely identify this key
     * @param fieldType
     */
    ID3v23FieldKey(String frameId, String subId, Id3FieldType fieldType)
    {
        this.frameId = frameId;
        this.subId = subId;
        this.fieldType = fieldType;

        this.fieldName = frameId + ":" + subId;
    }

    /**
     * @return fieldtype
     */
    public Id3FieldType getFieldType()
    {
        return fieldType;
    }

    /**
     * This is the frame identifier used to write the field
     *
     * @return
     */
    public String getFrameId()
    {
        return frameId;
    }

    /**
     * This is the subfield used within the frame for this type of field
     *
     * @return subId
     */
    public String getSubId()
    {
        return subId;
    }

    /**
     * This is the value of the key that can uniquely identifer a key type
     *
     * @return
     */
    public String getFieldName()
    {
        return fieldName;
    }

    static Map<String,ID3v23FieldKey> frameIdFieldKeyMapping = new LinkedHashMap<String,ID3v23FieldKey>();

    static
    {
        for(ID3v23FieldKey field:ID3v23FieldKey.values())
        {
            if(field.getSubId()!=null)
            {
                frameIdFieldKeyMapping.put(field.getFrameId() + field.getSubId(), field);
            }
            else
            {
                frameIdFieldKeyMapping.put(field.getFrameId(), field);
            }

        }
    }

    public static ID3v23FieldKey getFieldKeyFromFrameId(String frameId)
    {
        return frameIdFieldKeyMapping.get(frameId);
    }
}
