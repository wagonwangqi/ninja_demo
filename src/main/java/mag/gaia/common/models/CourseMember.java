package mag.gaia.common.models;

import javax.persistence.*;

@Entity
@Table(name = "courseMember")
public class CourseMember {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long courseId;
    private Long memberId;
}
