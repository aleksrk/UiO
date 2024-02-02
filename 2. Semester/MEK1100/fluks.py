def rektangel_fluks(rektangel):
    sider_rektangel = np.zeros(4)
    x1, y1 = rektangel[0], rektangel[1]
    x2, y2 = rektangel[2], rektangel[3]
    sider_rektangel = [0,0,0,0]
    for i in range(x1, x2+1):
        sider_rektangel[0] -= v[y1][i] * 0.5
        sider_rektangel[2] += v[y2][i] * 0.5
    for i in range(y1, y2+1):
        sider_rektangel[1] += u[i][x2] * 0.5
        sider_rektangel[3] -= u[i][x1] * 0.5

    return np.sum(sider_rektangel), sider_rektangel
