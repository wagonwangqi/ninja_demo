package mag.gaia.common.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "gaia_resetting_request")
public class ResettingRequest {

    public ResettingRequest(){
        this.uid       = UUID.randomUUID().toString();
        this.created   = new Date();
        this.modified  = this.created;
        this.status    = true;
        this.enabled   = true;
        this.remark    = "";
        this.sequence  = 0;

        this.successed = Boolean.FALSE;
        this.resetted  = Boolean.FALSE;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Member member;
    private String token;

    @Column(name="expire_date")
    private Date expireDate;//default to 6 hours

    @Column(name = "request_ip")
    private String requestIp;

    @Column(name = "request_headers")
    private String requestHeaders;

    @Column(name = "resetting_ip")
    private String resettingIp;

    @Column(name = "resetting_headers")
    private String resettingHeaders;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "resetting_date")
    private Date resettingDate;

    private Boolean resetted;//当用户进入重设密码表单后，resetted为true，重调密码链接失效
    private Boolean successed;//用户重设密码功能后，设置为true


    private Date created;
    private Date modified;
    private Boolean status;
    private Boolean enabled;
    private String remark;
    private String uid;
    private Integer sequence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getResettingIp() {
        return resettingIp;
    }

    public void setResettingIp(String resettingIp) {
        this.resettingIp = resettingIp;
    }

    public String getResettingHeaders() {
        return resettingHeaders;
    }

    public void setResettingHeaders(String resettingHeaders) {
        this.resettingHeaders = resettingHeaders;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResettingDate() {
        return resettingDate;
    }

    public void setResettingDate(Date resettingDate) {
        this.resettingDate = resettingDate;
    }

    public Boolean getResetted() {
        return resetted;
    }

    public void setResetted(Boolean resetted) {
        this.resetted = resetted;
    }

    public Boolean getSuccessed() {
        return successed;
    }

    public void setSuccessed(Boolean successed) {
        this.successed = successed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResettingRequest that = (ResettingRequest) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ResettingRequest{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", resetted=" + resetted +
                ", successed=" + successed +
                '}';
    }
}
