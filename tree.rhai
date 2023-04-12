let proj = new_java_project();

let req_1 = new_docs_grader()
    .project(proj)
    .files(["BinarySearchTree.java"])
    .out_of(15.0)
    .req_name("JavaDoc for `BinarySearchTree`")
    .penalty(3.0)
    .run();

let req_2 = new_by_hidden_test_grader()
    .url("https://ummfiles.fra1.digitaloceanspaces.com/grading_scripts/trees/BinarySearchTreeHiddenTest.java")
    .test_class_name("BinarySearchTreeHiddenTest")
    .out_of(85.0)
    .req_name("Hidden Tests for `BinarySearchTree`")
    .run();

let reqs = [req_1, req_2];
show_results(reqs);
generate_feedback(reqs);

let total = 0.0;
let out_of = 0.0;
for req in reqs {
    total = total + req.grade();
    out_of = out_of + req.out_of();
}

if total > (0.7 * out_of) {
    print("p;" + total.to_int())
} else {
    print("np")
}