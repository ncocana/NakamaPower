package edu.craptocraft.nakamapower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataIntegrityViolationException;

import edu.craptocraft.nakamapower.entity.Countries;
import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.service.CountriesService;
import edu.craptocraft.nakamapower.service.FriendshipsService;
import edu.craptocraft.nakamapower.service.UsersService;

@SpringBootApplication
public class App implements CommandLineRunner 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private CountriesService serviceCountries;

    @Autowired
    private UsersService serviceUsers;

    @Autowired
    private FriendshipsService serviceFriendships;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            Countries[] countries = {
                new Countries("ES", "Spain"),
                new Countries("DE", "Germany"),
                new Countries("RU", "Russia"),
                new Countries("AU", "Australia"),
                new Countries("CN", "China"),
                new Countries("JP", "Japan"),
                new Countries("TH", "Thailand"),
                new Countries("IN", "India"),
                new Countries("MY", "Malaysia"),
                new Countries("KR", "Korea"),
                new Countries("HK", "Hong Kong"),
                new Countries("TW", "Taiwan"),
                new Countries("PH", "Philippines"),
                new Countries("VN", "Vietnam"),
                new Countries("FR", "France"),
                new Countries("EU", "Europe"),
                new Countries("SE", "Sweden"),
                new Countries("IT", "Italy"),
                new Countries("GR", "Greece"),
                new Countries("AT", "Austria"),
                new Countries("GB", "United Kingdom"),
                new Countries("NL", "Netherlands"),
                new Countries("BE", "Belgium"),
                new Countries("CH", "Switzerland"),
                new Countries("AE", "United Arab Emirates"),
                new Countries("IL", "Israel"),
                new Countries("UA", "Ukraine"),
                new Countries("KZ", "Kazakhstan"),
                new Countries("PT", "Portugal"),
                new Countries("SA", "Saudi Arabia"),
                new Countries("DK", "Denmark"),
                new Countries("IR", "Iran"),
                new Countries("NO", "Norway"),
                new Countries("US", "United States"),
                new Countries("MX", "Mexico"),
                new Countries("CA", "Canada"),
                new Countries("A1", "Anonymous Proxy"),
                new Countries("SY", "Syrian Arab Republic"),
                new Countries("CY", "Cyprus"),
                new Countries("CZ", "Czech Republic"),
                new Countries("IQ", "Iraq"),
                new Countries("TR", "Turkey"),
                new Countries("RO", "Romania"),
                new Countries("LB", "Lebanon"),
                new Countries("HU", "Hungary"),
                new Countries("GE", "Georgia"),
                new Countries("BR", "Brazil"),
                new Countries("AZ", "Azerbaijan"),
                new Countries("A2", "Satellite Provider"),
                new Countries("PS", "Palestinian Territory"),
                new Countries("LT", "Lithuania"),
                new Countries("OM", "Oman"),
                new Countries("SK", "Slovakia"),
                new Countries("RS", "Serbia"),
                new Countries("FI", "Finland"),
                new Countries("IS", "Iceland"),
                new Countries("BG", "Bulgaria"),
                new Countries("SI", "Slovenia"),
                new Countries("MD", "Moldova"),
                new Countries("MK", "Macedonia"),
                new Countries("LI", "Liechtenstein"),
                new Countries("JE", "Jersey"),
                new Countries("PL", "Poland"),
                new Countries("HR", "Croatia"),
                new Countries("BA", "Bosnia and Herzegovina"),
                new Countries("EE", "Estonia"),
                new Countries("LV", "Latvia"),
                new Countries("JO", "Jordan"),
                new Countries("KG", "Kyrgyzstan"),
                new Countries("RE", "Reunion"),
                new Countries("IE", "Ireland"),
                new Countries("LY", "Libya"),
                new Countries("LU", "Luxembourg"),
                new Countries("AM", "Armenia"),
                new Countries("YE", "Yemen"),
                new Countries("BY", "Belarus"),
                new Countries("GI", "Gibraltar"),
                new Countries("MQ", "Martinique"),
                new Countries("PA", "Panama"),
                new Countries("DO", "Dominican Republic"),
                new Countries("GU", "Guam"),
                new Countries("PR", "Puerto Rico"),
                new Countries("VI", "Virgin Island"),
                new Countries("MN", "Mongolia"),
                new Countries("NZ", "New Zealand"),
                new Countries("SG", "Singapore"),
                new Countries("ID", "Indonesia"),
                new Countries("NP", "Nepal"),
                new Countries("PG", "Papua New Guinea"),
                new Countries("PK", "Pakistan"),
                new Countries("AP", "Asia/Pacific Region"),
                new Countries("BS", "Bahamas"),
                new Countries("LC", "Saint Lucia"),
                new Countries("AR", "Argentina"),
                new Countries("BD", "Bangladesh"),
                new Countries("TK", "Tokelau"),
                new Countries("KH", "Cambodia"),
                new Countries("MO", "Macau"),
                new Countries("MV", "Maldives"),
                new Countries("AF", "Afghanistan"),
                new Countries("NC", "New Caledonia"),
                new Countries("FJ", "Fiji"),
                new Countries("WF", "Wallis and Futuna"),
                new Countries("QA", "Qatar"),
                new Countries("AL", "Albania"),
                new Countries("BZ", "Belize"),
                new Countries("UZ", "Uzbekistan"),
                new Countries("KW", "Kuwait"),
                new Countries("ME", "Montenegro"),
                new Countries("PE", "Peru"),
                new Countries("BM", "Bermuda"),
                new Countries("CW", "Curacao"),
                new Countries("CO", "Colombia"),
                new Countries("VE", "Venezuela"),
                new Countries("CL", "Chile"),
                new Countries("EC", "Ecuador"),
                new Countries("ZA", "South Africa"),
                new Countries("IM", "Isle of Man"),
                new Countries("BO", "Bolivia"),
                new Countries("GG", "Guernsey"),
                new Countries("MT", "Malta"),
                new Countries("TJ", "Tajikistan"),
                new Countries("SC", "Seychelles"),
                new Countries("BH", "Bahrain"),
                new Countries("EG", "Egypt"),
                new Countries("ZW", "Zimbabwe"),
                new Countries("LR", "Liberia"),
                new Countries("KE", "Kenya"),
                new Countries("GH", "Ghana"),
                new Countries("NG", "Nigeria"),
                new Countries("TZ", "Tanzania"),
                new Countries("ZM", "Zambia"),
                new Countries("MG", "Madagascar"),
                new Countries("AO", "Angola"),
                new Countries("NA", "Namibia"),
                new Countries("CI", "Cote D'Ivoire"),
                new Countries("SD", "Sudan"),
                new Countries("CM", "Cameroon"),
                new Countries("MW", "Malawi"),
                new Countries("GA", "Gabon"),
                new Countries("ML", "Mali"),
                new Countries("BJ", "Benin"),
                new Countries("TD", "Chad"),
                new Countries("BW", "Botswana"),
                new Countries("CV", "Cape Verde"),
                new Countries("RW", "Rwanda"),
                new Countries("CG", "Congo"),
                new Countries("UG", "Uganda"),
                new Countries("MZ", "Mozambique"),
                new Countries("GM", "Gambia"),
                new Countries("LS", "Lesotho"),
                new Countries("MU", "Mauritius"),
                new Countries("MA", "Morocco"),
                new Countries("DZ", "Algeria"),
                new Countries("GN", "Guinea"),
                new Countries("SZ", "Swaziland"),
                new Countries("BF", "Burkina Faso"),
                new Countries("SL", "Sierra Leone"),
                new Countries("SO", "Somalia"),
                new Countries("NE", "Niger"),
                new Countries("CF", "Central African Republic"),
                new Countries("TG", "Togo"),
                new Countries("BI", "Burundi"),
                new Countries("GQ", "Equatorial Guinea"),
                new Countries("SS", "South Sudan"),
                new Countries("SN", "Senegal"),
                new Countries("MR", "Mauritania"),
                new Countries("DJ", "Djibouti"),
                new Countries("KM", "Comoros"),
                new Countries("IO", "British Indian Ocean Territory"),
                new Countries("TN", "Tunisia"),
                new Countries("GL", "Greenland"),
                new Countries("VA", "Holy See (Vatican City State)"),
                new Countries("CR", "Costa Rica"),
                new Countries("KY", "Cayman Islands"),
                new Countries("JM", "Jamaica"),
                new Countries("GT", "Guatemala"),
                new Countries("MH", "Marshall Islands"),
                new Countries("AQ", "Antarctica"),
                new Countries("BB", "Barbados"),
                new Countries("AW", "Aruba"),
                new Countries("MC", "Monaco"),
                new Countries("AI", "Anguilla"),
                new Countries("KN", "Saint Kitts and Nevis"),
                new Countries("GD", "Grenada"),
                new Countries("PY", "Paraguay"),
                new Countries("MS", "Montserrat"),
                new Countries("TC", "Turks and Caicos Islands"),
                new Countries("AG", "Antigua and Barbuda"),
                new Countries("TV", "Tuvalu"),
                new Countries("PF", "French Polynesia"),
                new Countries("SB", "Solomon Islands"),
                new Countries("VU", "Vanuatu"),
                new Countries("ER", "Eritrea"),
                new Countries("TT", "Trinidad and Tobago"),
                new Countries("AD", "Andorra"),
                new Countries("HT", "Haiti"),
                new Countries("SH", "Saint Helena"),
                new Countries("FM", "Micronesi"),
                new Countries("SV", "El Salvador"),
                new Countries("HN", "Honduras"),
                new Countries("UY", "Uruguay"),
                new Countries("LK", "Sri Lanka"),
                new Countries("EH", "Western Sahara"),
                new Countries("CX", "Christmas Island"),
                new Countries("WS", "Samoa"),
                new Countries("SR", "Suriname"),
                new Countries("CK", "Cook Islands"),
                new Countries("KI", "Kiribati"),
                new Countries("NU", "Niue"),
                new Countries("TO", "Tonga"),
                new Countries("TF", "French Southern Territories"),
                new Countries("YT", "Mayotte"),
                new Countries("NF", "Norfolk Island"),
                new Countries("VA", "Holy See"),
                new Countries("FK", "Falkland Islands (Malvinas)"),
                new Countries("PW", "Palau"),
                new Countries("NC", "New Caledonia"),
                new Countries("WF", "Wallis and Futuna"),
                new Countries("AS", "American Samoa"),
                new Countries("IO", "British Indian Ocean Territory"),
                new Countries("WS", "Samoa"),
                new Countries("SH", "Saint Helena"),
                new Countries("PM", "Saint Pierre and Miquelon"),
                new Countries("YT", "Mayotte"),
                new Countries("GP", "Guadeloupe"),
                new Countries("GG", "Guernsey"),
                new Countries("JE", "Jersey"),
                new Countries("IM", "Isle of Man"),
                new Countries("BL", "Saint Barthelemy"),
                new Countries("MF", "Saint Martin"),
                new Countries("RS", "Serbia"),
                new Countries("SX", "Sint Maarten (Dutch part)"),
                new Countries("XK", "Kosovo")
            };
            
            for (Countries country : countries) {
                serviceCountries.create(country);
            }

            Users user1 = new Users("dragnvindr@favonius.mond", "Diluc Rangvindr", "password", new Countries("DE"));
            Users user2 = new Users("kalberich@favonius.mond", "Kaeya Alberich", "password", new Countries("DE"));
            Users user3 = new Users("atartaglia@fatui.sn", "Tartaglia", "password", new Countries("RU"));
            serviceUsers.create(user1);
            serviceUsers.create(user2);
            serviceUsers.create(user3);

            Friendships friendship1 = new Friendships(new Users(3), new Users(2));
            Friendships friendship2 = new Friendships(new Users(3), new Users(1));
            serviceFriendships.create(friendship1);
            serviceFriendships.create(friendship2);
        } catch (DataIntegrityViolationException e) {
            // Initial data has already been created.
            // Do nothing.
        }
    }

}
