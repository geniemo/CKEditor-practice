import React from "react";
import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { Base64UploadAdapter } from "@ckeditor/ckeditor5-upload";

const Editor = () => {
  ClassicEditor.create(document.querySelector("#editor"), {
    plugins: [Base64UploadAdapter /* ... */],
    toolbar: [
      /* ... */
    ],
  })
    .then(/* ... */)
    .catch(/* ... */);

  return (
    <div className="Editor">
      <section>
        <div className="title-wrapper">
          <textarea className="input-title" placeholder="제목을 입력하세요" />
        </div>
      </section>
      <section>
        <CKEditor
          editor={ClassicEditor}
          data=""
          config={{ plugins: [Base64UploadAdapter] }}
          onReady={(editor) => {
            // You can store the "editor" and use when it is needed.
            console.log("Editor is ready to use!", editor);
          }}
          onChange={(event, editor) => {
            const data = editor.getData();
            console.log({ event, editor, data });
          }}
          onBlur={(event, editor) => {
            console.log("Blur.", editor);
          }}
          onFocus={(event, editor) => {
            console.log("Focus.", editor);
          }}
        />
      </section>
    </div>
  );
};

export default Editor;
