package redmine.model.user.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public enum Language {
    AUTO("", "auto"),
    SQ("Albanian", "Shqip"),
    AR("Arabic", "عربي"),
    AZ("Azerbaijani", "Azeri"),
    EU("Basque", "Euskara"),
    BS("Bosnian", "Bosanski"),
    BG ("Bulgarian", "Български"),
    CA ("Catalan", "Català"),
    ZH("Chinese/Simplified", "简体中文"),
    ZH_TW ("Chinese/Traditional", "繁體中文"),
    HR ("Croatian", "Hrvatski"),
    CS("Czech", "Čeština"),
    DA ("Danish", "Dansk"),
    NL("Dutch", "Nederlands"),
    EN ("English", ""),
    EN_GB ("English", "British"),
    ET("Estonian", "Eesti"),
    FI("Finnish", "Suomi");


    String internationalName;
    String localizedName;
}
