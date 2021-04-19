package redmine.db.request;

import lombok.SneakyThrows;
import redmine.managers.Manager;
import redmine.model.project.Project;

import java.sql.*;
import java.time.Instant;

public class ProjectRequests {
    @SneakyThrows
    public static Project create(Project project) {
        String query = "INSERT INTO public.projects " +
                "(id, name, description, homepage, is_public, created_on, updated_on, identifier, status, lft, rgt) " +
                "VALUES(DEFAULT,?,?,?,?,?,?,?,?,3,3);";
        Instant instantNow = Instant.now();
        Timestamp now = Timestamp.from(instantNow);
        PreparedStatement preparedStatement = Manager.dbConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, project.getName());
        preparedStatement.setString(2, project.getDescription());
        preparedStatement.setString(3, project.getHomepage());
        preparedStatement.setBoolean(4, project.getIsPublic());
        preparedStatement.setTimestamp(5, now);
        preparedStatement.setTimestamp(6, now);
        preparedStatement.setString(7, project.getIdentifier());
        preparedStatement.setInt(8, project.getStatus());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            project.setId(resultSet.getInt(1));
            project.setCreatedOn(instantNow);
            project.setUpdatedOn(instantNow);
        }
        return project;
    }

    @SneakyThrows
    public static Project getByName(String projectName) {
        String query = "SELECT * FROM projects WHERE name=? LIMIT 1;";
        PreparedStatement preparedStatement = Manager.dbConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, projectName);
        ResultSet resultSet = preparedStatement.executeQuery();
        Project project = new Project();
        if (resultSet.next()) {
            project.setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setDescription(resultSet.getString("description"))
                    .setHomepage(resultSet.getString("homepage"))
                    .setIsPublic(resultSet.getBoolean("is_public"))
                    .setCreatedOn(resultSet.getTimestamp("created_on").toInstant())
                    .setUpdatedOn(resultSet.getTimestamp("updated_on").toInstant())
                    .setIdentifier(resultSet.getString("identifier"))
                    .setStatus(resultSet.getInt("status"))
                    .setLft(resultSet.getInt("lft"))
                    .setRgt(resultSet.getInt("rgt"))
                    .setInheritMembers(resultSet.getBoolean("inherit_members"))
                    .setDefaultVersionId(resultSet.getInt("default_version_id"))
                    .setDefaultAssignedToId(resultSet.getInt("default_assigned_to_id"));
            //  project.setMembers(MemberRequests.getByProject(project));
        }
        return project;
    }

//    private static Instant toInstant(Date date){
//        return Instant.ofEpochMilli(date.getTime());
//    }
}
