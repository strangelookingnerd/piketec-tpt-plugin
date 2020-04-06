/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014-2020 PikeTec GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.piketec.tpt.api;

import java.net.URI;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

/**
 * RMI representation of a requirement. This interface allows more manipulation than the user can do
 * from the UI. The UI only allows globally removing an attribute and editing the comment. <br>
 * <br>
 * <b>Note:</b> Changing the requirement via API - in contrast to changing it by importing
 * requirements - will not lead to a "modified" state of the requirement. Only UI actions that lead
 * to a "modified" state will do so via API as well, e.g. setting status to "deleted". Otherwise use
 * {@link #markAsModified()} to set the status to modified explicitly.
 */
public interface Requirement extends IdentifiableRemote {

  /**
   * We distinguish between three types of requirements. Two of them are only informative.
   */
  enum RequirementType {

    /**
     * A real requirement.
     */
    REQUIREMENT("Requirement"),
    /**
     * A requirement used as a heading
     */
    HEADING("Heading"),
    /**
     * A requirement used as informative text.
     */
    INFORMATION("Information");

    private final String displayName;

    RequirementType(String name) {
      this.displayName = name;
    }

    public String getDisplayName() {
      return displayName;
    }
  }

  enum RequirementStatus {

    /**
     * A new requirement.
     */
    NEW("New"),
    /**
     * A normal requirement.
     */
    NORMAL("Normal"),
    /**
     * A deleted requirement.
     */
    DELETED("Deleted");

    private final String displayName;

    RequirementStatus(String name) {
      this.displayName = name;
    }

    public String getDiplayableName() {
      return displayName;
    }

  }

  /**
   * Get the type of the requirement.
   * 
   * @return The type of the requirement.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  RequirementType getType() throws ApiException, RemoteException;

  /**
   * Set the type of the requirement.
   * 
   * @param type
   *          The new type of the Requirement
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  void setType(RequirementType type) throws ApiException, RemoteException;

  /**
   * Every requirement has a unique ID.
   * 
   * @return The unique ID.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  String getID() throws ApiException, RemoteException;

  /**
   * A requirement normally belongs to a module. The default module is an empty string.
   * 
   * @return The name of the module the requirement belongs to.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  String getModule() throws ApiException, RemoteException;

  /**
   * Get the describing text a requirement normally has.
   * 
   * @return The describing text of the requirement.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  String getText() throws ApiException, RemoteException;

  /**
   * Set the describing text a requirement normally has.
   * 
   * @param text
   *          The new describing text. <code>Null</code> will be reduced to an empty string.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  void setText(String text) throws ApiException, RemoteException;

  /**
   * Get the comment of the requirement. This comment is also editable from the UI.
   * 
   * @return The comment of the requirement.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  String getComment() throws ApiException, RemoteException;

  /**
   * Set the comment of the requirement. This comment is also editable from the UI.
   * 
   * @param comment
   *          The new comment of the requirement. <code>Null</code> will be reduced to an empty
   *          string.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  void setComment(String comment) throws ApiException, RemoteException;

  /**
   * Get the URI of the requirement. If a requirement has a URI a clickable link symbol will be
   * displayed in the UI.
   * 
   * @return The URI of the requirement or <code>null</code>.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  URI getURI() throws ApiException, RemoteException;

  /**
   * Set the URI of the requirement. If a requirement has a URI a clickable link symbol will be
   * displayed in the UI.
   * 
   * @param uri
   *          The new URI or <code>null</code> to remove the link.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  void setURI(URI uri) throws ApiException, RemoteException;

  /**
   * Returns the attributes and the associated values of the requirement. A requirement can have
   * additional attributes. Each attribute has a value associated for the requirement.
   * 
   * @return A map from attribute name to value.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  Map<String, String> getAttributes() throws ApiException, RemoteException;

  /**
   * Set the attribute value of the requirement. A requirement can have additional attributes. Each
   * attribute has a value associated for the requirement. If the attribute does not exist yet it
   * will be created. If you set the <code>value</code> to <code>null</code> the attribute will be
   * removed. If you remove an attribute the associated attachments will be removed, too.
   * 
   * @param attributeName
   *          the name of the attribute.
   * @param value
   *          The new value or <code>null</code> to remove the attribute from the requirement.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  void setAttribute(String attributeName, String value) throws ApiException, RemoteException;

  /**
   * Delivers the list of attachments the requirement directly has. This list is shown in the UI as
   * attachments to the attribute &quot;Text&quot;. For attachments of other attributes see
   * {@link #getAttributeAttachments(String)}.
   * 
   * @return The List of attachments of the requirement.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  RemoteList<Attachment> getRequirementAttachments() throws ApiException, RemoteException;

  /**
   * Adds a new attachment to the list of attachments every requirement can have directly. This list
   * is shown in the UI as attachments to the attribute &quot;Text&quot;. For attachments of other
   * attributes see {@link #createAttributeAttachment(String, String, byte[])}.
   * 
   * @param fileName
   *          The file name of the attachment. The file name is used to create temporary files when
   *          viewing the attachment and will be shown in the UI as tooltip in TPT.
   * @param content
   *          The content of the attachment.
   * @return The newly created attachment
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  Attachment createRequirementAttachment(String fileName, byte[] content)
      throws ApiException, RemoteException;

  /**
   * Delivers the list of attachments every requirement attribute can have. The default attributes
   * "ID", "Text", "URI", and "Comment" cannot have attachments but attachments of the requirement
   * (see {@link #getRequirementAttachments()}) will be shown in the UI as attachments of "Text".
   * 
   * @param attributeName
   *          The name of the attribute.
   * @return The List of attachments of the given attribute. Returns <code>null</code> if the
   *         attribute is not defined for the requirement.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  RemoteList<Attachment> getAttributeAttachments(String attributeName)
      throws ApiException, RemoteException;

  /**
   * Adds a new attachment to the list of attachments every requirement attribute can have. If no
   * attribute with the given attribute name exists for the requirement the attribute will be
   * created and an empty value associated. The default attributes "ID", "Text", "URI", and
   * "Comment" cannot have attachments but attachments created with
   * {@link #createRequirementAttachment(String, byte[])} will be shown as attachments of "Text".
   * 
   * @param attributeName
   *          The name of the attribute.
   * @param fileName
   *          The file name of the attachment. The file name is used to create temporary files when
   *          viewing the attachment and will be shown as tooltip in TPT.
   * @param content
   *          The content of the attachment.
   * @return The newly created attachment
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  Attachment createAttributeAttachment(String attributeName, String fileName, byte[] content)
      throws ApiException, RemoteException;

  /**
   * Get all assesslets linked to this requirement.
   * 
   * @return The list of linked assessments.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  RemoteCollection<Assessment> getLinkedAssessments() throws ApiException, RemoteException;

  /**
   * Creates a link between this requirement and the given {@link Assessment}. If the argument is a
   * {@link AssessmentGroup} all assessments of the group will be added recursively. You can remove
   * a link by removing the <code>Assessment</code> from {@link #getLinkedAssessments()}.
   * 
   * @param aog
   *          The {@link Assessment} or {@link AssessmentGroup} to create links to this requirement.
   * @throws ApiException
   *           If the {@link RequirementType type} of the requirement is not linkable or if aog is
   *           not of the same project as the requirement.
   * @throws RemoteException
   *           remote communication problem
   */
  void linkAssessment(AssessmentOrGroup aog) throws ApiException, RemoteException;

  /**
   * Get all test cases and variants linked to this requirement.
   * 
   * @return The list of linked test cases and variants.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  RemoteCollection<Scenario> getLinkedScenarios() throws ApiException, RemoteException;

  /**
   * Creates a link between this requirement and the given {@link Scenario}. If the argument is a
   * {@link ScenarioGroup} all scenarios of the group will be added recursively. You can remove a
   * link by removing the <code>Scenario</code> from {@link #getLinkedScenarios()}.
   * 
   * @param sog
   *          The {@link Scenario} or {@link ScenarioOrGroup} to create links to this requirement.
   * @throws ApiException
   *           If the {@link RequirementType type} of the requirement is not linkable or if sog is
   *           not of the same project as the requirement.
   * @throws RemoteException
   *           remote communication problem
   */
  void linkScenario(ScenarioOrGroup sog) throws ApiException, RemoteException;

  /**
   * Sets the requirement status.
   * 
   * @param status
   *          to be set.
   * @throws ApiException
   *           If the the given status is not allowed or <code>null</code>.
   * @throws RemoteException
   *           remote communication problem
   */
  void setStatus(RequirementStatus status) throws ApiException, RemoteException;

  /**
   * Returns the current requirement status.
   * 
   * @return the current requirement status.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  RequirementStatus getStatus() throws ApiException, RemoteException;

  /**
   * Marks this requirement as modified.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   * 
   * @see #markAsReviewed()
   */
  void markAsModified() throws ApiException, RemoteException;

  /**
   * Marks this requirement as reviewed.
   * 
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   * @see #markAsModified()
   */
  void markAsReviewed() throws ApiException, RemoteException;

  /**
   * Returns <code>true</code> if the requirement is marked as modified, otherwise
   * <code>false</code>.
   * 
   * @return <code>true</code> if requirement is marked as modified, otherwise <code>false</code>
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  boolean isModified() throws ApiException, RemoteException;

  /**
   * Returns all (explicit and implicit) to this {@link Requirement} linked {@link Scenario test
   * cases}.
   * 
   * @return all (explicit and implicit) to this {@link Requirement} linked {@link Scenario test
   *         cases}.
   * 
   * @throws RemoteException
   *           remote communication problem
   * @throws ApiException
   *           API constraint error
   */
  Set<Scenario> getLinkedTestCases() throws ApiException, RemoteException;

}
