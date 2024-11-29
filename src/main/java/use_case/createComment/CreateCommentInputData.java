package use_case.createComment;

import entity.User;

public class CreateCommentInputData {
    private final String entryID;
    private final User author;
    private final String content;
    private final String commentDate;
    private final String attachmentPath;
    private final String fileType;

    public CreateCommentInputData(String entryID, User author, String content,
                                  String commentDate, String attachmentPath, String fileType) {
        this.entryID = entryID;
        this.author = author;
        this.content = content;
        this.commentDate = commentDate;
        this.attachmentPath = attachmentPath;
        this.fileType = fileType;
        }

        public String getEntryID() {
            return entryID;
        }

        public User getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public String getAttachmentPath() {
            return attachmentPath;
        }

        public String getFileType() {
            return fileType;
        }
    }
