package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Address;
import com.dbz.bl.intermediates.RealTable.Membership;
import com.dbz.bl.intermediates.RealTable.Person;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.VirtualTable.MembershipRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 4/10/2016.
 */
public class GetMailingListQuery extends Query {

    @Override
    public String getQuery() {
        StringBuilder query = new StringBuilder();
        query.append( "SELECT ")
             .append(Person.FIRST_NAME).append(",")
             .append(Person.MIDDLE_INITIAL).append(",")
             .append(Person.LAST_NAME).append(",")
             .append(Address.STREET_1).append(",")
             .append(Address.STREET_2).append(",")
             .append(Address.CITY).append(",")
             .append(Address.STATE).append(",")
             .append(Address.ZIP_CODE).append(",")
             .append(Membership.EXPIRATION_DATE)
             .append(" FROM ").append(Membership.class.getSimpleName())
             .append(" INNER JOIN ").append(Person.class.getSimpleName())
             .append(" ON ").append(Membership.class.getSimpleName()).append(".").append(Membership.PERSON_ID)
             .append("=").append(Person.class.getSimpleName()).append(".").append("ID")
             .append(" INNER JOIN ").append(Address.class.getSimpleName())
             .append(" ON ").append(Person.class.getSimpleName()).append(".").append(Person.ADDRESS_ID)
             .append("=").append(Address.class.getSimpleName()).append(".").append("ID");

        return query.toString();
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        String fullName = String.join(" ", rs.getString(Person.FIRST_NAME), rs.getString(Person.MIDDLE_INITIAL),  rs.getString(Person.LAST_NAME) );

        return new MembershipRecord(fullName, rs.getString(Address.STREET_1), rs.getString(Address.STREET_2),
                                  rs.getString(Address.CITY), rs.getString(Address.STATE),
                                  rs.getString(Address.ZIP_CODE), rs.getDate(Membership.EXPIRATION_DATE) );
    }
}
