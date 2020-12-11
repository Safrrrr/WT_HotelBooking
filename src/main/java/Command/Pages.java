package Command;

public enum Pages {
    SIGNIN_PAGE("/jsp/common/login.jsp"),
    SIGNUP_PAGE("/jsp/common/registration.jsp"),
    ADMIN_PAGE("/jsp/admin/admin-main.jsp"),
    USER_MAIN_PAGE("/jsp/user/user-main.jsp"),
    USER_PROFILE_PAGE("/jsp/user/user-prof.jsp"),
    BOOKINGS_PAGE("/jsp/user/bookings.jsp"),
    INDEX_PAGE ("/index.jsp"),
    PAGE ("page");

    private String value;

    Pages(String value) {
        this.value = value;
    }


}
