package com.smartjinyu.mybookshelf.model.bean;

import android.support.annotation.Nullable;
import android.util.Log;

import com.github.promeg.pinyinhelper.Pinyin;
import com.smartjinyu.mybookshelf.util.SharedPrefUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by smartjinyu on 2017/1/19.
 * This class represents a simple book.
 */

public class Book implements Serializable {
    private static final String TAG = "Book";

    private String title;
    private String imgUrl;
    private UUID id; // A unique id to identify each book
    private List<String> authors;
    private List<String> translators;
    private Map<String, String> WebIds;
    // "douban"
    private String publisher;
    private Calendar pubTime;
    private Calendar addTime;// Time the book add to bookshelf
    private String isbn;
    private boolean hasCover;
    private int readingStatus;
    /**
     * 0 represents not set
     * 1 represents unread
     * 2 represents reading
     * 3 represents read
     */

    private UUID bookshelfID;
    private String notes;
    private String website;
    private List<UUID> labelID;

    public Book() {
        this(UUID.randomUUID());
    }

    public Book(UUID uuid) {
        id = uuid;
        bookshelfID = UUID.fromString("407c4479-5a57-4371-8b94-ad038f1276fe");
        //default bookshelf id
        readingStatus = 0;
        addTime = Calendar.getInstance();

        // initial value
        authors = new ArrayList<>();
        translators = new ArrayList<>();
        labelID = new ArrayList<>();
        WebIds = new HashMap<>();
    }


    public String getCoverPhotoFileName() {
        return "Cover_" + id.toString() + ".jpg";
    }

    public String getCoverPhotoFileNameWithoutExtension() {
        return "Cover_" + id.toString();
    }


    public Calendar getAddTime() {
        return addTime;
    }

    public void setAddTime(Calendar addTime) {//only used for recovering data from database
        this.addTime = addTime;
    }

    public Map<String, String> getWebIds() {
        return WebIds;
    }

    public void setWebIds(Map<String, String> webIds) {
        WebIds = webIds;
    }

    public UUID getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Calendar getPubTime() {
        return pubTime;
    }

    public void setPubTime(Calendar pubTime) {
        this.pubTime = pubTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getTranslators() {
        return translators;
    }

    public void setTranslators(List<String> translators) {
        this.translators = translators;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public UUID getBookshelfID() {
        return bookshelfID;
    }

    public void setBookshelfID(UUID bookshelfID) {
        this.bookshelfID = bookshelfID;
    }

    public int getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(int readingStatus) {
        this.readingStatus = readingStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<UUID> getLabelID() {
        return labelID;
    }

    public void addLabel(Label label) {
        if (labelID == null) {
            labelID = new ArrayList<>();
        }
        if (!labelID.contains(label.getId())) {
            labelID.add(label.getId());
        }
    }

    public void addLabel(UUID labelID) {
        if (this.labelID == null) {
            this.labelID = new ArrayList<>();
        }
        if (!this.labelID.contains(labelID)) {
            this.labelID.add(labelID);
        }
    }

    public void removeLabel(UUID labelID) {
        if (this.labelID != null) {
            this.labelID.remove(labelID);
        }
    }

    public void removeLabel(Label label) {
        if (labelID != null) {
            labelID.remove(label.getId());
        }
    }


    public void setLabelID(List<UUID> labelID) {
        this.labelID = labelID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static class titleComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            // transfer to Chinese Pinyin
            String title1 = Pinyin.toPinyin(book1.getTitle(), "");
            String title2 = Pinyin.toPinyin(book2.getTitle(), "");
            return title1.compareTo(title2);
        }
    }

    public static class publisherComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            // transfer to Chinese Pinyin
            String publisher1 = Pinyin.toPinyin(book1.getPublisher(), "");
            String publisher2 = Pinyin.toPinyin(book2.getPublisher(), "");
            return publisher1.compareTo(publisher2);
        }
    }

    public static class pubtimeComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            Calendar pubtime1 = book1.getPubTime();
            Calendar pubtime2 = book2.getPubTime();
            return pubtime1.compareTo(pubtime2);
        }
    }

    public static class authorComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            String author1 = Pinyin.toPinyin(book1.getAuthors().toString(), "");
            //Log.d(TAG,"Title1 = " + book1.getTitle() + ", Author1 Pinyin = " + author1);
            String author2 = Pinyin.toPinyin(book2.getAuthors().toString(), "");
            //Log.d(TAG,"Title2 = " + book2.getTitle() + ", Author2 Pinyin = " + author2);
            int result = author1.compareTo(author2);
            //Log.d(TAG,"Compare result is " + result);
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return book.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Get Formatted authors information
     *
     * @return A string with different authors divided by ',', e.g.: smartjinyu1,smartjinyu2. Return null if no authors
     */
    @Nullable
    public String getFormatAuthor() {
        if (authors.size() != 0) {
            StringBuilder authorsBuilder = new StringBuilder();
            for (String curAuthor : getAuthors()) {
                authorsBuilder.append(curAuthor);
                authorsBuilder.append(",");
            }
            if (authorsBuilder.length() != 0) {
                authorsBuilder.deleteCharAt(authorsBuilder.length() - 1);
            }
            return authorsBuilder.toString();
        } else {
            return null;
        }
    }

    /**
     * Get Formatted translators information
     *
     * @return A string with translators authors divided by ',', e.g.: smartjinyu1,smartjinyu2. Return null if no translators
     */
    @Nullable
    public String getFormatTranslator() {
        if (translators.size() != 0) {
            StringBuilder translators = new StringBuilder();
            for (String translator : getTranslators()) {
                translators.append(translator);
                translators.append(",");
            }
            if (translators.length() != 0) {
                translators.deleteCharAt(translators.length() - 1);
            }
            return translators.toString();
        } else {
            return null;
        }
    }

    /**
     * Get the source of the book info
     *
     * @return "Douban.com","OpenLibrary.org","Manually"
     */
    public String getDataSource() {
        Map<String, String> webIds = getWebIds();
        if (webIds.size() == 0) {
            return "Manually";
        } else {
            for (String key : webIds.keySet()) {
                if (key.equals("douban")) {
                    return "Douban.com";
                }
                if (key.equals("openLibrary")) {
                    return "OpenLibrary.org";
                }
            }
            return "Manually";
        }
    }

    public static Book newInstance(OpenLibraryJson OLJ, String isbn) {
        if (OLJ == null) return null;
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(OLJ.getTitle());
        List<String> authors = new ArrayList<>();
        List<OpenLibraryJson.AuthorsBean> authorsBeen = OLJ.getAuthors();
        for (OpenLibraryJson.AuthorsBean ab : authorsBeen) {
            authors.add(ab.getName());
        }
        book.setAuthors(authors);
        // Open Library books are almost English books, no translators
        book.getWebIds().put("openLibrary", OLJ.getKey());
        book.setPublisher(OLJ.getPublishers().get(0).getName());
        String rawDate = OLJ.getPublish_date();
        int pubYear = 9999;
        if (rawDate.length() > 4) {
            pubYear = Integer.parseInt(rawDate.substring(rawDate.length() - 4));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(pubYear, 0, 1); // Open Library seldom returns month
        book.setPubTime(calendar);
        boolean addWebsite = SharedPrefUtil.getInstance().getBoolean(SharedPrefUtil.AC_WEBSITE, true);
        if (addWebsite) book.setWebsite("https://openlibrary.org" + OLJ.getKey());
        book.setImgUrl(OLJ.getCover().getLarge());
        return book;
    }

    public static Book newInstance(DouBanJson douBanJson, String isbn) {
        if (douBanJson == null) return null;
        Book book = new Book();
        book.setTitle(douBanJson.getTitle());
        //mBook.setId(Long.parseLong(response.body().getId(),10));
        book.setIsbn(isbn);
        if (douBanJson.getAuthor().size() != 0) {
            book.setAuthors(douBanJson.getAuthor());
        } else {
            book.setAuthors(new ArrayList<String>());
        }
        if (douBanJson.getTranslator().size() != 0) {
            book.setTranslators(douBanJson.getTranslator());
        } else {
            book.setTranslators(new ArrayList<String>());
        }

        book.getWebIds().put("douban", douBanJson.getId());
        book.setPublisher(douBanJson.getPublisher());

        String rawDate = douBanJson.getPubdate();
        Log.i(TAG, "Date raw = " + rawDate);
        String year, month;
        if (rawDate.contains("-")) {
            // 2016-11
            String[] date = rawDate.split("-");
            year = date[0];
            // rawDate sometimes is "2016-11", sometimes is "2000-10-1", sometimes is "2010-1"
            month = date[1];
        } else if (rawDate.contains(".")) {
            String[] date = rawDate.split("\\.");
            year = date[0];
            // rawDate sometimes is "2016-11", sometimes is "2000-10-1", sometimes is "2010-1"
            month = date[1];
        } else {
            year = "9999";
            month = "1";
        }
        Log.i(TAG, "Get PubDate Year = " + year + ", month = " + month);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
        book.setPubTime(calendar);
        book.setImgUrl(douBanJson.getImages().getLarge());
        boolean addWebsite = SharedPrefUtil.getInstance().getBoolean(SharedPrefUtil.AC_WEBSITE, true);
        if (addWebsite) book.setWebsite("https://book.douban.com/subject/" + douBanJson.getId());
        return book;
    }
}
