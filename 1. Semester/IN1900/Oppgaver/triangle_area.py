# Problem 4.4 Compute the area of an arbitrary triangle

def triangle_area(vertices):
    A = (1/2)*( vertices[1][0] * vertices[2][1]
              - vertices[2][0] * vertices[1][1]
              - vertices[0][0] * vertices[2][1]
              + vertices[2][0] * vertices[0][1]
              + vertices[0][0] * vertices[1][1]
              - vertices[1][0] * vertices[0][1])
    return A

def test_triangle_area():
    """
    Verify the area of a triangle with vertices
    (0,0), (1,0), and (0,2).
    """
    v1 = [0,0];  v2 = [1,0];  v3 = [0,2]
    vertices = [v1, v2, v3]
    expected = 1
    computed = triangle_area(vertices)
    tol = 1E-14
    success = abs(expected - computed) < tol
    msg = f"computed area={computed} != {expected}(expected)"
    assert success, msg

"""
============================= test session starts ==============================
platform darwin -- Python 3.7.3, pytest-6.0.2, py-1.9.0, pluggy-0.13.1
rootdir: /Users/kvale/Documents/UiO/IN1900/Oppgaver
collected 1 item

triangle_area.py .                                                       [100%]

============================== 1 passed in 0.01s ===============================
"""
